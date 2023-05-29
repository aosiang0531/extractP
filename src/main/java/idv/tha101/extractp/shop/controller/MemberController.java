package idv.tha101.extractp.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import idv.tha101.extractp.base.controller.BaseController;
import idv.tha101.extractp.shop.pojo.MemberVO;
import idv.tha101.extractp.shop.service.MemberService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("member")
public class MemberController extends BaseController<MemberVO> {
	
	@Autowired
	private MemberService service;
	
	@PostMapping("/login")
    public String login(HttpSession session, @RequestParam("memberId") int memberId) {
        // 創建假的已登入會員
        MemberVO fakeMember = new MemberVO();
        fakeMember.setId(memberId);

        // 將假的會員放入 HttpSession
        session.setAttribute("member", fakeMember);

        // 返回登入成功頁面
        return "login-success";
    }
	
	@Override
	@GetMapping
	public List<MemberVO> findAll() {
		return	service.findAll();
	}

	@Override
	@GetMapping("/{id}")
	public MemberVO findById(@PathVariable(value = "id") int id) {
		return service.findById(id);
	}

	@Override
	@PostMapping
	public MemberVO save(@RequestBody MemberVO product) {

	return service.saveOrUpdate(product);
	}
	
	@Override
	@PutMapping("/{id}")
	public MemberVO update(@RequestBody MemberVO vo, @PathVariable(value = "id") int id) {
		return service.saveOrUpdate(vo.setId(id));
	}
	
	@Override
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value = "id")int id) {
		service.deleteById(id);
	}


}
