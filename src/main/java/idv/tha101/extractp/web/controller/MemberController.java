package idv.tha101.extractp.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import idv.tha101.extractp.base.controller.BaseController;
import idv.tha101.extractp.web.pojo.MemberVO;
import idv.tha101.extractp.web.service.MemberService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("member")
public class MemberController extends BaseController<MemberVO> {

	@Autowired
	private MemberService memberservice;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@GetMapping
	public List<MemberVO> findAll() {
		return memberservice.findAll();
	}

	@Override
	@GetMapping("/{id}")
	public MemberVO findById(@PathVariable(value = "id") int id) {
		return memberservice.findById(id);
	}

	@Override
	@PostMapping
	public MemberVO save(@RequestBody MemberVO member) {

		return memberservice.saveOrUpdate(member);
	}

	@Override
	@PutMapping("/{id}")
	public MemberVO update(@RequestBody MemberVO vo, @PathVariable(value = "id") int id) {
		if(vo.getPassword()!=null) {
			vo.setPassword(passwordEncoder.encode(vo.getPassword()));
		}
		vo.setId(id);
		return memberservice.saveOrUpdate(vo);
	}

	@Override
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value = "id") int id) {
		memberservice.deleteById(id);
	}

	@PostMapping("/register")
	public MemberVO register(@RequestBody MemberVO member) {
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		return memberservice.register(member);
	}

	@PostMapping("/login")
	public ResponseEntity<MemberVO> login(@RequestBody MemberVO member, HttpSession session) {
		String email = member.getEmail();
		String password = member.getPassword();

		if (email == null || password == null) {
			return ResponseEntity.badRequest().body(null);
		}

		MemberVO memberVO = memberservice.findByEmail(email);
		if (memberVO == null) {
			return ResponseEntity.badRequest().body(null);
		}

		if (!memberVO.getPassword().equals(password)) {
			return ResponseEntity.badRequest().body(null);
		}

		session.setAttribute("memberId", memberVO.getId());
		session.setAttribute("memberEmail", memberVO.getEmail());
		session.setAttribute("memberName", memberVO.getName());
		session.setAttribute("memberImg", memberVO.getImage());

		System.out.println("memberId: " + session.getAttribute("memberId"));
		System.out.println("memberEmail: " + session.getAttribute("memberEmail"));
		System.out.println("memberName: " + session.getAttribute("memberName"));

		return ResponseEntity.ok(memberVO);
	}

//	// Sending a simple Email
//    @PostMapping("/sendMail")
//    public String
//    sendMail(@RequestBody MemberEmailDTO details)
//    {
//        String status
//            = emailService.sendSimpleMail(details);
// 
//        return status;
//    }
}
