package idv.tha101.extractp.web.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.ArticleCommentRepository;
import idv.tha101.extractp.web.pojo.ArticleCommentVO;
import idv.tha101.extractp.web.service.ArticleCommentService;

@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

	@Autowired
	private ArticleCommentRepository articleCommentRepository;

	@Override
	public List<ArticleCommentVO> findAll() {
		return articleCommentRepository.findAll();
	}

	@Override
	public ArticleCommentVO findById(Integer id) {
		return articleCommentRepository.findById(id).orElseThrow();
	}

	@Override
	public ArticleCommentVO saveOrUpdate(ArticleCommentVO vo) {
		if (vo.getId() != null) {
			Optional<ArticleCommentVO> optionalVO = articleCommentRepository.findById(vo.getId());
			if (optionalVO.isPresent()) {
				ArticleCommentVO existingVO = optionalVO.get();

				Class<?> voClass = ArticleCommentVO.class;
				Field[] fields = voClass.getDeclaredFields();

				for (Field field : fields) {
					field.setAccessible(true);

					try {
						Object updateValues = field.get(vo);
						if (updateValues != null) {
							field.set(existingVO, updateValues);
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				return articleCommentRepository.save(existingVO);
			} else {
				return null;
			}
		} else {
			return articleCommentRepository.save(vo);
		}
	}

	@Override
	public void deleteById(Integer id) {
		articleCommentRepository.deleteById(id);

	}

}
