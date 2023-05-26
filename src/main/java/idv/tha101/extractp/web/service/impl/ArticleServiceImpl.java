package idv.tha101.extractp.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.ArticleRepository;
import idv.tha101.extractp.web.pojo.ArticleVO;
import idv.tha101.extractp.web.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{

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
	public ArticleVO save(ArticleVO vo) {
		return articleRepository.save(vo);
	}

	@Override
	public void delete(Integer id) {
		articleRepository.deleteById(id);
		
	}

}
