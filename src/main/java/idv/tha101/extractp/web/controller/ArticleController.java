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
import idv.tha101.extractp.web.pojo.ArticleVO;
import idv.tha101.extractp.web.service.ArticleService;
import idv.tha101.extractp.web.service.ArticleTagService;

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
	
//	@GetMapping("temlatest")
//	public Collection<ArticleDTO> findTemLatestArticle(){
//		return articleService.findTemLatestArticle();
//	};
	
//	@GetMapping("/{memberid}")
//	public List<ArticleVO> findByMemberId(int memberId){
//		return articleService.findByMemberId(memberId);
//	}
	
	


}
