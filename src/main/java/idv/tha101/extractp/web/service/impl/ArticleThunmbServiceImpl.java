package idv.tha101.extractp.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.ArticleThunmbRepository;
import idv.tha101.extractp.web.pojo.ArticleThunmbVO;
import idv.tha101.extractp.web.service.ArticleThunmbService;

@Service
public class ArticleThunmbServiceImpl implements ArticleThunmbService{
	
	@Autowired
	private ArticleThunmbRepository articleThunmbRepository;

	@Override
	public List<ArticleThunmbVO> findAll() {
		return articleThunmbRepository.findAll();
	}

	@Override
	public ArticleThunmbVO findById(Integer id) {
		return articleThunmbRepository.findById(id).orElseThrow();
	}

	@Override
	public ArticleThunmbVO save(ArticleThunmbVO vo) {
		return articleThunmbRepository.save(vo);
	}

	@Override
	public void delete(Integer id) {
		articleThunmbRepository.deleteById(id);
		
	}

}
