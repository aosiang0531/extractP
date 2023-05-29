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
import idv.tha101.extractp.web.pojo.ArticleCommentReportVO;
import idv.tha101.extractp.web.service.ArticleCommentReportService;

@RestController
@RequestMapping("article_comment_report")
public class ArticleCommentReportController extends BaseController<ArticleCommentReportVO>{

	@Autowired
	private ArticleCommentReportService articleCommentReportService;
	
	@Override
	@GetMapping
	public List<ArticleCommentReportVO> findAll() {
		// TODO Auto-generated method stub
		return articleCommentReportService.findAll();
	}

	@Override
	@GetMapping("/{id}")
	public ArticleCommentReportVO findById(@PathVariable(value = "id") int id) {
		// TODO Auto-generated method stub
		return articleCommentReportService.findById(id);
	}

	@Override
	@PostMapping
	public ArticleCommentReportVO save(@RequestBody ArticleCommentReportVO vo) {
		// TODO Auto-generated method stub
		return articleCommentReportService.saveOrUpdate(vo);
	}

	@Override
	@PutMapping("/{id}")
	public ArticleCommentReportVO update(@RequestBody ArticleCommentReportVO vo, @PathVariable(value = "id") int id) {
		// TODO Auto-generated method stub
		return articleCommentReportService.saveOrUpdate(vo.setId(id));
	}

	@Override
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value = "id") int id) {
		articleCommentReportService.deleteById(id);
		
	}

}
