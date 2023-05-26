package idv.tha101.extractp.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.ArticleTagRepository;
import idv.tha101.extractp.web.pojo.ArticleTagVO;
import idv.tha101.extractp.web.service.ArticleTagService;

@Service
public class ArticleTagServiceImpl implements ArticleTagService{

	@Autowired
	private ArticleTagRepository articleTagRepository;
	
	@Override
	public List<ArticleTagVO> findAll() {
		return articleTagRepository.findAll();
	}

	@Override
	public ArticleTagVO findById(Integer id) {
		return articleTagRepository.findById(id).orElseThrow();
	}

	@Override
	public ArticleTagVO save(ArticleTagVO vo) {
		return articleTagRepository.save(vo);
	}

	@Override
	public void delete(Integer id) {
		articleTagRepository.deleteById(id);
		
	}

}
