package idv.tha101.extractp.web.service.impl;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.ArticleRepository;
import idv.tha101.extractp.web.dao.ArticleThunmbRepository;
import idv.tha101.extractp.web.dao.MemberArticleFavRepository;
import idv.tha101.extractp.web.pojo.ArticleDTO;
import idv.tha101.extractp.web.pojo.ArticleDTO2;
import idv.tha101.extractp.web.pojo.ArticleThunmbVO;
import idv.tha101.extractp.web.pojo.ArticleThunmbVO.ThumbPK;
import idv.tha101.extractp.web.pojo.ArticleVO;
import idv.tha101.extractp.web.pojo.MemberArticleFavVO;
import idv.tha101.extractp.web.pojo.MemberArticleFavVO.FavPk;
import idv.tha101.extractp.web.service.ArticleService;
import jakarta.transaction.Transactional;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private ArticleThunmbRepository articleThunmbRepository;

	@Autowired
	private MemberArticleFavRepository memberArticleFavRepository;

	@Override
	public List<ArticleVO> findAll() {
		return articleRepository.findAll();
	}

	@Override
	public ArticleVO findById(Integer id) {
		return articleRepository.findById(id).orElseThrow();
	}

	@Override
	@Transactional
	public ArticleVO saveOrUpdate(ArticleVO vo) {
		if (vo.getId() != null) {
			Optional<ArticleVO> optionalVO = articleRepository.findById(vo.getId());
			if (optionalVO.isPresent()) {
				ArticleVO existingVO = optionalVO.get();

				Class<?> voClass = ArticleVO.class;
				Field[] fields = voClass.getDeclaredFields();

				for (Field field : fields) {
					field.setAccessible(true);
					try {
						Object updatedValue = field.get(vo);
						if (updatedValue != null) {
							field.set(existingVO, updatedValue);
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}

				return articleRepository.save(existingVO);
			} else {
				return null;
			}
		} else {
			return articleRepository.save(vo);
		}
	}

	@Override
	public void deleteById(Integer id) {
		articleRepository.deleteById(id);

	}

	public Collection<ArticleDTO> findPopArticle() {
		return articleRepository.findPopArticle();
	};
	
	public Page<ArticleDTO> findPopArticle(Pageable pageable) {
		return articleRepository.findPopArticle(pageable);
	};

	public Collection<ArticleDTO> findLatestArticle() {
		return articleRepository.findLatestArticle();
	}

	@Override
	public Collection<ArticleDTO> findTemPop(Integer id) {
		return articleRepository.findTemPop(id);
	}

	@Override
	public Collection<ArticleDTO> findTemLatest(Integer id) {
		return articleRepository.findTemLatest(id);
	}

	@Override
	public List<ArticleVO> findByMemberId(Integer memberId) {
		return articleRepository.findByMemberId(memberId);
	}

	@Override
	public Collection<ArticleDTO> findGroupPop(Integer id) {
		return articleRepository.findGroupPop(id);
	}

	@Override
	public Collection<ArticleDTO> findGroupLatest(Integer id) {
		return articleRepository.findGroupLatest(id);
	}

	@Override
	public Collection<ArticleDTO2> findArticleDetailsById(Integer id) {
		return articleRepository.findArticleDetailsById(id);
	}

	@Override
	public List<ArticleVO> searchByArticleTitle(String keyword) {
		return articleRepository.findByArticleTitleContaining(keyword);
	}

	@Override
	public List<ArticleVO> searchByArticleContent(String keyword) {
		return articleRepository.findByArticleContentContaining(keyword);
	}
	
	
	// 按讚,新增文章讚數及新增thumb資料表
	@Override
	@Transactional
	public Map<String, Integer> thumbUp(Integer articleId, Integer memberId) {
		Map<String, Integer> map = new HashMap<>();
		if (articleThunmbRepository.existsById(new ThumbPK(memberId, articleId))) {
			map.put("result", 0);
		} else {
			ArticleVO article = articleRepository.getById(articleId);
//			System.out.println("AAAA:" + article);
			article.setThunmb_number(article.getThunmb_number() + 1);
			articleThunmbRepository.save(new ArticleThunmbVO().setPk(new ThumbPK(memberId, articleId)));
			map.put("result", 1);
		}
		return map;
	}
	
	// 收藏,新增文章收藏數及新增member_fav資料表
	@Override
	@Transactional
	public Map<String, Integer> memberFav(Integer articleId, Integer memberId) {
		Map<String, Integer> map = new HashMap<>();
		if (memberArticleFavRepository.existsById(new FavPk(memberId, articleId))) {
			map.put("result", 0);
		} else {
			ArticleVO article = articleRepository.getById(articleId);
			article.setMember_article_fav_number(article.getMember_article_fav_number() + 1);
			memberArticleFavRepository.save(new MemberArticleFavVO().setPk(new FavPk(memberId, articleId)));
			map.put("result", 1);
		}
		return map;
	}

}
