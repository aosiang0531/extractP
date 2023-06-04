package idv.tha101.extractp.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import idv.tha101.extractp.web.dao.ArticleHastagRepository;
import idv.tha101.extractp.web.pojo.ArticleHastagVO;
import idv.tha101.extractp.web.service.ArticleHastagService;

public class ArticleHastagServiceImpl implements ArticleHastagService{
	
	@Autowired
	private ArticleHastagRepository articleHastagRepository;

	@Override
	public List<ArticleHastagVO> findAll() {
		return articleHastagRepository.findAll();
	}

	@Override
	public ArticleHastagVO findById(Integer id) {
		return articleHastagRepository.findById(id).orElseThrow();
	}


	@Override
	public ArticleHastagVO saveOrUpdate(ArticleHastagVO vo) {
//	TODO
		return null;
	}

	@Override
	public void deleteById(Integer id) {
		articleHastagRepository.deleteById(id);
		
	}

}
