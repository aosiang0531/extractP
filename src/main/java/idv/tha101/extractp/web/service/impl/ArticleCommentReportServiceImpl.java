package idv.tha101.extractp.web.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.ArticleCommentReportRepository;
import idv.tha101.extractp.web.pojo.ArticleCommentReportVO;
import idv.tha101.extractp.web.pojo.ArticleReportVO;
import idv.tha101.extractp.web.service.ArticleCommentReportService;

@Service
public class ArticleCommentReportServiceImpl implements ArticleCommentReportService {

	@Autowired
	private ArticleCommentReportRepository articleCommentReportRepository;

	@Override
	public List<ArticleCommentReportVO> findAll() {
		return articleCommentReportRepository.findAll();
	}

	@Override
	public ArticleCommentReportVO findById(Integer id) {
		return articleCommentReportRepository.findById(id).orElseThrow();
	}

	@Override
	public ArticleCommentReportVO saveOrUpdate(ArticleCommentReportVO vo) {
		if (vo.getId() != null) {
			Optional<ArticleCommentReportVO> optionalVO = articleCommentReportRepository.findById(vo.getId());
			if (optionalVO.isPresent()) {
				ArticleCommentReportVO existingVO = optionalVO.get();

				Class<?> voClass = ArticleCommentReportVO.class;
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

				return articleCommentReportRepository.save(existingVO);
			} else {
				return null;
			}
		} else {
			return articleCommentReportRepository.save(vo);
		}
	}

	@Override
	public void deleteById(Integer id) {
		articleCommentReportRepository.deleteById(id);

	}

	@Override
	public List<ArticleCommentReportVO> findByArticleCommentReportStatus(String status) {
		return articleCommentReportRepository.findByArticleCommentReportStatus(status);
	}

}
