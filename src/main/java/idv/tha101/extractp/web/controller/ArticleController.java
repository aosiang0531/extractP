package idv.tha101.extractp.web.controller;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import idv.tha101.extractp.base.controller.BaseController;
import idv.tha101.extractp.web.pojo.ArticleDTO;
import idv.tha101.extractp.web.pojo.ArticleDTO2;
import idv.tha101.extractp.web.pojo.ArticleVO;
import idv.tha101.extractp.web.service.ArticleService;

@RestController
@RequestMapping("article")
public class ArticleController extends BaseController<ArticleVO> {

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
	public Collection<ArticleDTO> findPopArticle() {
		Collection<ArticleDTO> articleDTOs = articleService.findPopArticle();
		Collection<ArticleDTO> collection = articleDTOs.stream()
														.filter(a -> a.getarticle_is_hidden() != true)
														.toList();
		return collection;
	};
	
	@GetMapping("popPage")
	public Page<ArticleDTO> findPopArticleByPage(Pageable pageable) {
		Page<ArticleDTO> articleDTOs = articleService.findPopArticle(pageable);
//		Collection<ArticleDTO> collection = articleDTOs.stream()
//														.filter(a -> a.getarticle_is_hidden() != true)
//														.toList();
		return articleDTOs;
	};
	
	
	

	@GetMapping("latest")
	public Collection<ArticleDTO> findLatestArticle() {
		Collection<ArticleDTO> articleDTOs = articleService.findLatestArticle();
		Collection<ArticleDTO> collection = articleDTOs.stream()
														.filter(a -> a.getarticle_is_hidden() != true)
														.toList();
		
		return collection;
	};

	@GetMapping("/temPop/{article_template_id}")
	public Collection<ArticleDTO> findTemPop(@PathVariable(value = "article_template_id") int id) {
		Collection<ArticleDTO> articleDTOs = articleService.findTemPop(id);
		Collection<ArticleDTO> collection = articleDTOs.stream()
														.filter(a -> a.getarticle_is_hidden() != true)
														.toList();
		
		return collection;
	};

	@GetMapping("/temLatest/{article_template_id}")
	public Collection<ArticleDTO> findTemLatest(@PathVariable(value = "article_template_id") int id) {
		Collection<ArticleDTO> articleDTOs = articleService.findTemLatest(id);
		Collection<ArticleDTO> collection = articleDTOs.stream()
														.filter(a -> a.getarticle_is_hidden() != true)
														.toList();
		
		return collection;
	};

	// 以會員ID找到文章
	@GetMapping("/memberId/{memberId}")
	public List<ArticleVO> findByMemberId(@PathVariable(value = "memberId") int id) {
		List<ArticleVO> articleVOs = articleService.findByMemberId(id);
		List<ArticleVO> list = articleVOs.stream()
										.filter(a -> a.getIs_hidden() != true)
										.collect(Collectors.toList());
		return list;
	}

	@GetMapping("/groupPop/{article_group_id}")
	public Collection<ArticleDTO> findGroupPop(@PathVariable(value = "article_group_id") int id) {
		return articleService.findGroupPop(id);
	}

	@GetMapping("/groupLatest/{article_group_id}")
	public Collection<ArticleDTO> findGroupLatest(@PathVariable(value = "article_group_id") int id) {
		return articleService.findGroupLatest(id);
	}

	// 顯示特定完整文章
	@GetMapping("/detailsById/{article_id}")
	public Collection<ArticleDTO2> findArticleDetailsById(@PathVariable(value = "article_id") int id) {
		return articleService.findArticleDetailsById(id);
	}

	// 標題模糊查詢
	@GetMapping("searchTitle")
	public List<ArticleVO> searchArticleTitle(@RequestParam("keyword") String keyword) {
		List<ArticleVO> articleVOs = articleService.searchByArticleTitle(keyword);
		List<ArticleVO> list = articleVOs.stream()
										.filter(a -> a.getIs_hidden() != true)
										.collect(Collectors.toList());
		return list;
	}

	@GetMapping("searchContent")
	public List<ArticleVO> searchByArticleContent(@RequestParam("keyword") String keyword) {
		return articleService.searchByArticleContent(keyword);
	}

	@PostMapping("thumbUp")
	public Map<String, Integer> saveThumb(@RequestBody Map<String, Integer> map) {
		return articleService.thumbUp(map.get("article_id"), map.get("member_id"));
	}

	@PostMapping("memberFav")
	public Map<String, Integer> saveMemberFav(@RequestBody Map<String, Integer> map) {
		return articleService.memberFav(map.get("article_id"), map.get("member_id"));
	}

}
