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
import idv.tha101.extractp.web.pojo.ArticleCommentDTO;
import idv.tha101.extractp.web.pojo.ArticleCommentVO;
import idv.tha101.extractp.web.service.ArticleCommentService;

@RestController
@RequestMapping("article_comment")
public class ArticleCommentController extends BaseController<ArticleCommentVO> {

	@Autowired
	private ArticleCommentService articleCommentService;

	@Override
	@GetMapping
	public List<ArticleCommentVO> findAll() {
		return articleCommentService.findAll();
	}

	@Override
	@GetMapping("/{id}")
	public ArticleCommentVO findById(@PathVariable(value = "id") int id) {
		return articleCommentService.findById(id);
	}

	@Override
	@PostMapping
	public ArticleCommentVO save(@RequestBody ArticleCommentVO vo) {
		return articleCommentService.saveOrUpdate(vo);
	}

	@Override
	@PutMapping("/{id}")
	public ArticleCommentVO update(@RequestBody ArticleCommentVO vo, @PathVariable(value = "id") int id) {
		return articleCommentService.saveOrUpdate(vo.setId(id));
	}

	@Override
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value = "id") int id) {
		articleCommentService.deleteById(id);

	}
	
	@GetMapping("/findByArticleId/{article_comment_article_id}")
	public List<ArticleCommentDTO> findByArticleId(@PathVariable(value = "article_comment_article_id") int id) {
		return articleCommentService.findByArticleId(id);
	}

}
