package idv.tha101.extractp.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.ArticleReportRepository;
import idv.tha101.extractp.web.pojo.ArticleReportVO;
import idv.tha101.extractp.web.service.ArticleReportService;

@Service
public class ArticleReportServiceImpl implements ArticleReportService{

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
	public ArticleReportVO save(ArticleReportVO vo) {
		return articleReportRepository.save(vo);
	}

	@Override
	public void delete(Integer id) {
		articleReportRepository.deleteById(id);
		
	}

}
