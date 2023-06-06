package idv.tha101.extractp.web.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.ArticleReportRepository;
import idv.tha101.extractp.web.pojo.ArticleReportVO;
import idv.tha101.extractp.web.service.ArticleReportService;

@Service
public class ArticleReportServiceImpl implements ArticleReportService {

	@Autowired
	private ArticleReportRepository articleReportRepository;

	@Override
	public List<ArticleReportVO> findAll() {
		return articleReportRepository.findAll();
	}

	@Override
	public ArticleReportVO findById(Integer id) {
		return articleReportRepository.findById(id).orElseThrow();
	}

	@Override
	public ArticleReportVO saveOrUpdate(ArticleReportVO vo) {
		if (vo.getId() != null) {
			Optional<ArticleReportVO> optionalVO = articleReportRepository.findById(vo.getId());
			if (optionalVO.isPresent()) {
				ArticleReportVO existingVO = optionalVO.get();

				Class<?> voClass = ArticleReportVO.class;
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

				return articleReportRepository.save(existingVO);
			} else {
				return null;
			}
		} else {
			return articleReportRepository.save(vo);
		}
	}

	@Override
	public void deleteById(Integer id) {
		articleReportRepository.deleteById(id);

	}

	@Override
	public List<ArticleReportVO> findByStatus(String reportStatus) {
		return articleReportRepository.findByArticleReportStatus(reportStatus);
	}

}
