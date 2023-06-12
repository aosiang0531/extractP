package idv.tha101.extractp.base.service;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import idv.tha101.extractp.base.dao.TokenRepository;
import idv.tha101.extractp.base.pojo.AuthenticationRequest;
import idv.tha101.extractp.base.pojo.AuthenticationResponse;
import idv.tha101.extractp.base.pojo.RegisterRequest;
import idv.tha101.extractp.base.pojo.Token;
import idv.tha101.extractp.base.pojo.TokenType;
import idv.tha101.extractp.web.dao.MemberRepository;
import idv.tha101.extractp.web.pojo.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final MemberRepository repository;
	private final TokenRepository tokenRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthenticationResponse register(MemberVO vo) {
		if(repository.existsByEmail(vo.getEmail())) {
			return null;
		}else {
			vo.setPassword(passwordEncoder.encode(vo.getPassword()));
			var savedUser = repository.save(vo);
			var jwtToken = jwtService.generateToken(vo);
			var refreshToken = jwtService.generateRefreshToken(vo);
			saveUserToken(savedUser, jwtToken);
			return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).memberId(savedUser.getId()).build();			
		}
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user = repository.findByEmail(request.getEmail()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(user);
		revokeAllUserTokens(user);
		saveUserToken(user, jwtToken);
		return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).memberId(user.getId()).build();
	}

	private void saveUserToken(MemberVO user, String jwtToken) {
		var token = Token.builder().user(user).token(jwtToken).token_type(TokenType.BEARER).expired(false).revoked(false)
				.build();
		tokenRepository.save(token);
	}

	private void revokeAllUserTokens(MemberVO user) {
		var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
		if (validUserTokens.isEmpty())
			return;
		validUserTokens.forEach(token -> {
			token.setExpired(true);
			token.setRevoked(true);
		});
		tokenRepository.saveAll(validUserTokens);
	}

	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String refreshToken;
		final String userEmail;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return;
		}
		refreshToken = authHeader.substring(7);
		userEmail = jwtService.extractUsername(refreshToken);
		if (userEmail != null) {
			var user = this.repository.findByEmail(userEmail).orElseThrow();
			if (jwtService.isTokenValid(refreshToken, user)) {
				var accessToken = jwtService.generateToken(user);
				revokeAllUserTokens(user);
				saveUserToken(user, accessToken);
				var authResponse = AuthenticationResponse.builder().accessToken(accessToken).refreshToken(refreshToken)
						.build();
				new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
			}
		}
	}

	public MemberVO findMemberByToken(String token) {
		return tokenRepository.findByToken(token).orElseThrow().getUser();
	}
}