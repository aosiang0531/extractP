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
import idv.tha101.extractp.web.pojo.ArticleThunmbVO;
import idv.tha101.extractp.web.service.ArticleThunmbService;

@RestController
@RequestMapping("article_thumb")
public class ArticleThunmbController extends BaseController<ArticleThunmbVO>{
	
	@Autowired
	private ArticleThunmbService articleThunmbService;

	@Override
	@GetMapping
	public List<ArticleThunmbVO> findAll() {
		// TODO Auto-generated method stub
		return articleThunmbService.findAll();
	}

	@Override
	@GetMapping("/{id}")
	public ArticleThunmbVO findById(@PathVariable(value = "id") int id) {
		// TODO Auto-generated method stub
		return articleThunmbService.findById(id);
	}

	@Override
	@PostMapping
	public ArticleThunmbVO save(@RequestBody ArticleThunmbVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@PutMapping("/{id}")
	public ArticleThunmbVO update(@RequestBody ArticleThunmbVO vo, @PathVariable(value = "id") int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value = "id") int id) {
		articleThunmbService.deleteById(id);
	}

}
