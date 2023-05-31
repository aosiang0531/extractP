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
import idv.tha101.extractp.web.pojo.ArticleGroupVO;
import idv.tha101.extractp.web.service.ArticleGroupService;

@RestController
@RequestMapping("article_group")
public class ArticleGroupController extends BaseController<ArticleGroupVO> {

	@Autowired
	private ArticleGroupService articleGroupService;

	@GetMapping
	public List<ArticleGroupVO> findAll() {
		return articleGroupService.findAll();
	}

	@GetMapping("/{id}")
	public ArticleGroupVO findById(@PathVariable(value = "id") int id) {
		return articleGroupService.findById(id);
	}

	@PostMapping
	public ArticleGroupVO save(@RequestBody ArticleGroupVO vo) {
		return articleGroupService.saveOrUpdate(vo);
	}

	@Override
	@PutMapping("/{id}")
	public ArticleGroupVO update(@RequestBody ArticleGroupVO vo, @PathVariable(value = "id") int id) {
		return articleGroupService.saveOrUpdate(vo.setId(id));
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value = "id") int id) {
		articleGroupService.deleteById(id);

	}

}
