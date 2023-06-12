package idv.tha101.extractp.base.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import idv.tha101.extractp.base.pojo.AuthenticationRequest;
import idv.tha101.extractp.base.pojo.AuthenticationResponse;
import idv.tha101.extractp.base.service.AuthenticationService;
import idv.tha101.extractp.base.service.LogoutService;
import idv.tha101.extractp.web.pojo.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService service;

	private final LogoutService logoutService;

	@GetMapping
	public MemberVO getToken(@RequestParam(name = "token") String token) throws IOException {
		return service.findMemberByToken(token);
	}

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody MemberVO request) {
		return ResponseEntity.ok(service.register(request));
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(service.authenticate(request));
	}

	@PostMapping("/refresh-token")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		service.refreshToken(request, response);
	}

	@GetMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		logoutService.logout(request, response, authentication);
	}

}