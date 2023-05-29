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
import idv.tha101.extractp.web.pojo.ArticleTagVO;
import idv.tha101.extractp.web.service.ArticleTagService;

@RestController
@RequestMapping("article_tag")
public class ArticleTagController extends BaseController<ArticleTagVO>{

	@Autowired
	private ArticleTagService articleTagService;
	
	@Override
	@GetMapping
	public List<ArticleTagVO> findAll() {
		return articleTagService.findAll();
	}

	@Override
	@GetMapping("/{id}")
	public ArticleTagVO findById(@PathVariable(value = "id") int id) {
		return articleTagService.findById(id);
	}

	@Override
	@PostMapping
	public ArticleTagVO save(@RequestBody ArticleTagVO vo) {
		return articleTagService.saveOrUpdate(vo);
	}

	@Override
	@PutMapping("/{id}")
	public ArticleTagVO update(@RequestBody ArticleTagVO vo, @PathVariable(value = "id") int id) {
		return articleTagService.saveOrUpdate(vo.setId(id));
	}

	@Override
	@DeleteMapping("/{id}")
	public void deleteById(int id) {
		articleTagService.deleteById(id);
		
	}

}
