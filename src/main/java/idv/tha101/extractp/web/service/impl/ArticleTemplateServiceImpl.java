package idv.tha101.extractp.web.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.ArticleTemplateRepository;
import idv.tha101.extractp.web.pojo.ArticleTemplateVO;
import idv.tha101.extractp.web.service.ArticleTemplateService;

@Service
public class ArticleTemplateServiceImpl implements ArticleTemplateService {

	@Autowired
	private ArticleTemplateRepository articleTemplateRepository;

	@Override
	public List<ArticleTemplateVO> findAll() {
		return articleTemplateRepository.findAll();
	}

	@Override
	public ArticleTemplateVO findById(Integer id) {
		return articleTemplateRepository.findById(id).orElseThrow();
	}

	@Override
	public ArticleTemplateVO saveOrUpdate(ArticleTemplateVO vo) {
		if (vo.getId() != null) {
			Optional<ArticleTemplateVO> optionalVO = articleTemplateRepository.findById(vo.getId());
			if (optionalVO.isPresent()) {
				ArticleTemplateVO existingVO = optionalVO.get();

				Class<?> voClass = ArticleTemplateVO.class;
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

				return articleTemplateRepository.save(existingVO);
			} else {
				return null;
			}
		} else {
			return articleTemplateRepository.save(vo);
		}
	}

	@Override
	public void deleteById(Integer id) {
		articleTemplateRepository.deleteById(id);

	}

}
