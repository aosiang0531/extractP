package idv.tha101.extractp.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import idv.tha101.extractp.base.controller.BaseController;
import idv.tha101.extractp.web.pojo.ArticleVO;
import idv.tha101.extractp.web.service.ArticleService;

@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController<ArticleVO>{
	
	@Autowired
	private ArticleService articleService;

	@GetMapping
	public List<ArticleVO> findAll() {
		return articleService.findAll();
	}

	@GetMapping({"/id"})
	public ArticleVO findById(@PathVariable(value = "id") int id) {
		return articleService.findById(id);
	}

	@GetMapping
	public ArticleVO save(@RequestBody ArticleVO vo) {
		return articleService.saveOrUpdate(vo);
	}

	@Override
	@GetMapping({"/id"})
	public ArticleVO update(@RequestBody ArticleVO vo, @PathVariable(value = "id") int id) {
		return articleService.saveOrUpdate(vo.setId(id));
	}
	
	@GetMapping({"/id"})
	public void deleteById(@PathVariable(value = "id") int id) {
		articleService.deleteById(id);
		
	}



}
