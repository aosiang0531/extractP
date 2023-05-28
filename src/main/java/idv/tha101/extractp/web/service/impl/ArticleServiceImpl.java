package idv.tha101.extractp.web.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.ArticleRepository;
import idv.tha101.extractp.web.pojo.ArticleVO;
import idv.tha101.extractp.web.service.ArticleService;

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

}
