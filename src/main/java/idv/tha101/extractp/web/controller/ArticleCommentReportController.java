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

	@PutMapping("review")
	@Transactional
	public Map<String, Integer> updateReportStatus(@RequestBody Map<String, String> map) {
		Map<String, Integer> result = new HashMap<>();
		int id = Integer.parseInt(map.get("article_comment_report_id"));
		boolean isApproved = Boolean.parseBoolean(map.get("is_approved"));
		if (isApproved) { // 檢舉通過
			articleCommentReportService.saveOrUpdate(new ArticleCommentReportVO().setId(id).setArticleCommentReportStatus("1"));
			ArticleCommentReportVO aCommentReportVO = articleCommentReportService.findById(id);
			ArticleCommentVO aCommentVO = articleCommentService.findById(aCommentReportVO.getArticle_comment_id());
			articleCommentService.saveOrUpdate(aCommentVO.setIs_hidden(true));
			if (aCommentVO.getIs_hidden()) {
				result.put("result", 1);
			}
		} else {// 檢舉未通過
			articleCommentReportService.saveOrUpdate(new ArticleCommentReportVO().setId(id).setArticleCommentReportStatus("2"));
			result.put("result", 1);
		}
		return result;
	}
	
	// 以狀態查詢留言檢舉
	@GetMapping("reportStatus/{reportStatus}")
	List<ArticleCommentReportVO> findByStatus(@PathVariable(value = "reportStatus")String reportStatus){
		return articleCommentReportService.findByArticleCommentReportStatus(reportStatus);
	}

}
