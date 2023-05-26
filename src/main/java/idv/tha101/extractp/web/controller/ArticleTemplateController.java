package idv.tha101.extractp.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping({"/id"})
	public ArticleTemplateVO findById(@PathVariable(value = "id") int id) {
		return articleTemplateService.findById(null);
	}

	@GetMapping
	public ArticleTemplateVO save(@RequestBody ArticleTemplateVO vo) {
		return articleTemplateService.save(vo);
	}

	@GetMapping({"/id"})
	public void deleteById(@PathVariable(value = "id") int id) {
		articleTemplateService.delete(id);		
	}

}
