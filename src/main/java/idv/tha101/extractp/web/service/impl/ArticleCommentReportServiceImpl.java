package idv.tha101.extractp.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.ArticleCommentReportRepository;
import idv.tha101.extractp.web.pojo.ArticleCommentReportVO;
import idv.tha101.extractp.web.service.ArticleCommentReportService;

@Service
public class ArticleCommentReportServiceImpl implements ArticleCommentReportService{

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
	public ArticleCommentReportVO save(ArticleCommentReportVO vo) {
		return articleCommentReportRepository.save(vo);
	}

	@Override
	public void delete(Integer id) {
		articleCommentReportRepository.deleteById(id);
		
	}

}
