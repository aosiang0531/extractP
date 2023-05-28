package idv.tha101.extractp.web.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.ArticleGroupRepository;
import idv.tha101.extractp.web.pojo.ArticleGroupVO;
import idv.tha101.extractp.web.service.ArticleGroupService;

@Service
public class ArticleGroupServiceImpl implements ArticleGroupService {

	@Autowired
	private ArticleGroupRepository articleGroupRepository;

	@Override
	public List<ArticleGroupVO> findAll() {
		return articleGroupRepository.findAll();
	}

	@Override
	public ArticleGroupVO findById(Integer id) {
		return articleGroupRepository.findById(id).orElseThrow();
	}

	@Override
	public ArticleGroupVO saveOrUpdate(ArticleGroupVO vo) {
		if (vo.getId() != null) {
			Optional<ArticleGroupVO> optionalVO = articleGroupRepository.findById(vo.getId());
			if (optionalVO.isPresent()) {
				ArticleGroupVO existingVO = optionalVO.get();

				Class<?> voClass = ArticleGroupVO.class;
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

				return articleGroupRepository.save(existingVO);
			} else {
				return null;
			}
		} else {
			return articleGroupRepository.save(vo);
		}

	}

	@Override
	public void deleteById(Integer id) {
		articleGroupRepository.deleteById(id);

	}

}
