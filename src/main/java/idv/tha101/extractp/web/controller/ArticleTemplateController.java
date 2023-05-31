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
import idv.tha101.extractp.web.pojo.ArticleTemplateVO;
import idv.tha101.extractp.web.service.ArticleTemplateService;

@RestController
@RequestMapping("article_template")
public class ArticleTemplateController extends BaseController<ArticleTemplateVO>{

	@Autowired
	private ArticleTemplateService articleTemplateService;
	
	@GetMapping
	public List<ArticleTemplateVO> findAll() {
		return articleTemplateService.findAll();
	}

	@GetMapping("/{id}")
	public ArticleTemplateVO findById(@PathVariable(value = "id") int id) {
		return articleTemplateService.findById(id);
	}

	@PostMapping
	public ArticleTemplateVO save(@RequestBody ArticleTemplateVO vo) {
		return articleTemplateService.saveOrUpdate(vo);
	}
	
	@Override
	@PutMapping("/{id}")
	public ArticleTemplateVO update(@RequestBody ArticleTemplateVO vo, @PathVariable(value = "id") int id) {
		return articleTemplateService.saveOrUpdate(vo.setId(id));
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value = "id") int id) {
		articleTemplateService.deleteById(id);		
	}



}
