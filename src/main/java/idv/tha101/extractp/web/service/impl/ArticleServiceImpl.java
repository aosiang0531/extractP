package idv.tha101.extractp.web.service.impl;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.ArticleRepository;
import idv.tha101.extractp.web.pojo.ArticleDTO;
import idv.tha101.extractp.web.pojo.ArticleDTO2;
import idv.tha101.extractp.web.pojo.ArticleVO;
import idv.tha101.extractp.web.service.ArticleService;
import jakarta.transaction.Transactional;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

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
	
	public Collection<ArticleDTO> findPopArticle(){
		return articleRepository.findPopArticle();
	};
	
	public Collection<ArticleDTO> findLatestArticle(){
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


}
