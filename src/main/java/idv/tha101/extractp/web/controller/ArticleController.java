package idv.tha101.extractp.web.controller;

import java.util.Collection;
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
import idv.tha101.extractp.web.pojo.ArticleDTO;
import idv.tha101.extractp.web.pojo.ArticleDTO2;
import idv.tha101.extractp.web.pojo.ArticleVO;
import idv.tha101.extractp.web.service.ArticleService;

@RestController
@RequestMapping("article")
public class ArticleController extends BaseController<ArticleVO>{
	
	@Autowired
	private ArticleService articleService;
//	private ArticleTagService articleTagService;

	@GetMapping
	public List<ArticleVO> findAll() {
		return articleService.findAll();
	}

	@GetMapping("/{id}")
	public ArticleVO findById(@PathVariable(value = "id") int id) {
		return articleService.findById(id);
	}

	@PostMapping
	public ArticleVO save(@RequestBody ArticleVO vo) {
		return articleService.saveOrUpdate(vo);
	}

	@Override
	@PutMapping("/{id}")
	public ArticleVO update(@RequestBody ArticleVO vo, @PathVariable(value = "id") int id) {
		return articleService.saveOrUpdate(vo.setId(id));
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value = "id") int id) {
		articleService.deleteById(id);
		
	}
	
	@GetMapping("pop")
	public Collection<ArticleDTO> findPopArticle(){
		return articleService.findPopArticle();
	};
	
	@GetMapping("latest")
	public Collection<ArticleDTO> findLatestArticle(){
		return articleService.findLatestArticle();
	};
	
	@GetMapping("/temPop/{article_template_id}")
	public Collection<ArticleDTO> findTemPop(@PathVariable(value = "article_template_id") int id){
		return articleService.findTemPop(id);
	};

	@GetMapping("/temLatest/{article_template_id}")
	public Collection<ArticleDTO> findTemLatest(@PathVariable(value = "article_template_id") int id){
		return articleService.findTemLatest(id);
	};
	
	@GetMapping("/memberId/{memberId}")
	public List<ArticleVO> findByMemberId(@PathVariable(value = "memberId") int id){
		return articleService.findByMemberId(id);
	}
	
	@GetMapping("/groupPop/{article_group_id}")
	public Collection<ArticleDTO> findGroupPop(@PathVariable(value = "article_group_id") int id){
		return articleService.findGroupPop(id);
	}
	
	@GetMapping("/groupLatest/{article_group_id}")
	public Collection<ArticleDTO> findGroupLatest(@PathVariable(value = "article_group_id") int id){
		return articleService.findGroupLatest(id);
	}

	@GetMapping("/detailsById/{article_id}")
	public Collection<ArticleDTO2> findArticleDetailsById(@PathVariable(value = "article_id")int id) {
		return articleService.findArticleDetailsById(id);
	}

	
	

}
