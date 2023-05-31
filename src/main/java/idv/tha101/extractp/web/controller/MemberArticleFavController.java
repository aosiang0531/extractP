package idv.tha101.extractp.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import idv.tha101.extractp.base.controller.BaseController;
import idv.tha101.extractp.web.pojo.MemberArticleFavVO;
import idv.tha101.extractp.web.service.MemberArticleFavService;

@RestController
@RequestMapping("member_article_fav")
public class MemberArticleFavController extends BaseController<MemberArticleFavVO>{

	@Autowired
	private MemberArticleFavService memberArticleFavService;
	
	@Override
	@GetMapping
	public List<MemberArticleFavVO> findAll() {
		// TODO Auto-generated method stub
		return memberArticleFavService.findAll();
	}

	@Override
	@GetMapping("/{id}")
	public MemberArticleFavVO findById(@PathVariable(value = "id") int id) {
		// TODO Auto-generated method stub
		return memberArticleFavService.findById(id);
	}

	@Override
	@PostMapping
	public MemberArticleFavVO save(@RequestBody MemberArticleFavVO vo) {
		// TODO Auto-generated method stub
		return memberArticleFavService.saveOrUpdate(vo);
	}

	@Override
	@PutMapping("/{id}")
	public MemberArticleFavVO update(MemberArticleFavVO vo, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@DeleteMapping("/{id}")
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

}
