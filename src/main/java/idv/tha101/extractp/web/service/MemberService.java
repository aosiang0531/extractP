package idv.tha101.extractp.web.service;

import idv.tha101.extractp.base.service.BaseService;
import idv.tha101.extractp.web.pojo.MemberVO;

public interface MemberService extends BaseService<MemberVO> {

	MemberVO register(MemberVO memberVO);

	MemberVO login(String email, String password);

	MemberVO findByEmail(String email);

	// Method
	// To send a simple email
//    String sendSimpleMail(MemberEmailDTO details);
}