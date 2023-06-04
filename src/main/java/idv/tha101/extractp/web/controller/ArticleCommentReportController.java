package idv.tha101.extractp.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import idv.tha101.extractp.web.pojo.ArticleCommentVO;
import idv.tha101.extractp.web.service.ArticleCommentReportService;
import idv.tha101.extractp.web.service.ArticleCommentService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("article_comment_report")
public class ArticleCommentReportController extends BaseController<ArticleCommentReportVO> {

	@Autowired
	private ArticleCommentReportService articleCommentReportService;

	@Autowired
	private ArticleCommentService articleCommentService;

	@Override
	@GetMapping
	public List<ArticleCommentReportVO> findAll() {
		return articleCommentReportService.findAll();
	}

	@Override
	@GetMapping("/{id}")
	public ArticleCommentReportVO findById(@PathVariable(value = "id") int id) {
		return articleCommentReportService.findById(id);
	}

	@Override
	@PostMapping
	public ArticleCommentReportVO save(@RequestBody ArticleCommentReportVO vo) {
		return articleCommentReportService.saveOrUpdate(vo);
	}

	@Override
	@PutMapping("/{id}")
	public ArticleCommentReportVO update(@RequestBody ArticleCommentReportVO vo, @PathVariable(value = "id") int id) {
		return articleCommentReportService.saveOrUpdate(vo.setId(id));
	}

	@Override
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value = "id") int id) {
		articleCommentReportService.deleteById(id);
	}

	@PutMapping("review/{id}")
	@Transactional
	public Map<String, Integer> updateReportStatus(@PathVariable(value = "id") int id) {
		System.out.println(id);
		articleCommentReportService.saveOrUpdate(new ArticleCommentReportVO().setId(id).setStatus("1"));
		ArticleCommentReportVO aCommentReportVO = articleCommentReportService.findById(id);
		ArticleCommentVO aCommentVO = articleCommentService.findById(aCommentReportVO.getArticle_comment_id());
		articleCommentService.saveOrUpdate(aCommentVO.setIs_hidden(true));
		Map<String, Integer> map = new HashMap<>();
		if (aCommentVO.getIs_hidden()) {
			map.put("result", 1);
		}
		return map;
	}

}
