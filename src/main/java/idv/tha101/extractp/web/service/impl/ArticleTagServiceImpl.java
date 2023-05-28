package idv.tha101.extractp.web.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.ArticleTagRepository;
import idv.tha101.extractp.web.pojo.ArticleTagVO;
import idv.tha101.extractp.web.service.ArticleTagService;

@Service
public class ArticleTagServiceImpl implements ArticleTagService {

	@Autowired
	private ArticleTagRepository articleTagRepository;

	@Override
	public List<ArticleTagVO> findAll() {
		return articleTagRepository.findAll();
	}

	@Override
	public ArticleTagVO findById(Integer id) {
		return articleTagRepository.findById(id).orElseThrow();
	}

	@Override
	public ArticleTagVO saveOrUpdate(ArticleTagVO vo) {
		if (vo.getId() != null) {
			Optional<ArticleTagVO> optionalVO = articleTagRepository.findById(vo.getId());
			if (optionalVO.isPresent()) {
				ArticleTagVO existingVO = optionalVO.get();

				Class<?> voClass = ArticleTagVO.class;
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

				return articleTagRepository.save(existingVO);
			} else {
				return null;
			}
		} else {
			return articleTagRepository.save(vo);
		}
	}

	@Override
	public void deleteById(Integer id) {
		articleTagRepository.deleteById(id);

	}

}
