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
import idv.tha101.extractp.web.pojo.ArticleReportVO;
import idv.tha101.extractp.web.pojo.ArticleVO;
import idv.tha101.extractp.web.service.ArticleReportService;
import idv.tha101.extractp.web.service.ArticleService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("article_report")
public class ArticleReportController extends BaseController<ArticleReportVO> {

	@Autowired
	private ArticleReportService articleReportService;
	
	@Autowired
	private ArticleService articleService;

	@Override
	@GetMapping
	public List<ArticleReportVO> findAll() {
		return articleReportService.findAll();
	}

	@Override
	@GetMapping("/{id}")
	public ArticleReportVO findById(@PathVariable(value = "id") int id) {
		return articleReportService.findById(id);
	}

	@Override
	@PostMapping
	public ArticleReportVO save(@RequestBody ArticleReportVO vo) {
		return articleReportService.saveOrUpdate(vo);
	}

	@Override
	@PutMapping("/{id}")
	public ArticleReportVO update(@RequestBody ArticleReportVO vo, @PathVariable(value = "id") int id) {
		return articleReportService.saveOrUpdate(vo.setId(id));
	}

	@Override
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value = "id") int id) {
		articleReportService.deleteById(id);
	}
	
	@PutMapping("review/{id}")
	@Transactional
	public Map<String, Integer> updateReportStatus(@PathVariable(value = "id") int id){
		System.out.println(id);
		articleReportService.saveOrUpdate(new ArticleReportVO().setId(id).setStatus("1"));
		ArticleReportVO aReportVO = articleReportService.findById(id);
		ArticleVO article = articleService.findById(aReportVO.getArticle_id());
		articleService.saveOrUpdate(article.setIs_hidden(true));
		Map<String, Integer> map = new HashMap<>();
		if(article.getIs_hidden()) {
			map.put("result", 1);
		}
		return map;
	}

}
