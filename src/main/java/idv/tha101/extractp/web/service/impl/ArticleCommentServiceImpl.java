package idv.tha101.extractp.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.ArticleCommentRepository;
import idv.tha101.extractp.web.pojo.ArticleCommentVO;
import idv.tha101.extractp.web.service.ArticleCommentService;

@Service
public class ArticleCommentServiceImpl implements ArticleCommentService{

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
	public ArticleCommentVO save(ArticleCommentVO vo) {
		return articleCommentRepository.save(vo);
	}

	@Override
	public void delete(Integer id) {
		articleCommentRepository.deleteById(id);
		
	}

}
