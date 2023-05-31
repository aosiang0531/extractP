package idv.tha101.extractp.member.controller;

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
import idv.tha101.extractp.member.pojo.MemberVO;
import idv.tha101.extractp.member.service.MemberService;

@RestController
@RequestMapping("member")
public class MemberController extends BaseController<MemberVO> {

	@Autowired
	private MemberService memberservice;

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
		return memberservice.saveOrUpdate(vo.setId(id));
	}

	@Override
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value = "id") int id) {
		memberservice.deleteById(id);
	}

}
