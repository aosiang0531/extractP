package idv.tha101.extractp.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.ArticleGroupRepository;
import idv.tha101.extractp.web.pojo.ArticleGroupVO;
import idv.tha101.extractp.web.service.ArticleGroupService;

@Service
public class ArticleGroupServiceImpl implements ArticleGroupService{
	
	@Autowired
	private ArticleGroupRepository articleGroupRepository;

	@Override
	public List<ArticleGroupVO> findAll() {
		return articleGroupRepository.findAll();
	}

	@Override
	public ArticleGroupVO findById(Integer id) {
		return articleGroupRepository.findById(id).orElseThrow();
	}

	@Override
	public ArticleGroupVO save(ArticleGroupVO vo) {
		return articleGroupRepository.save(vo);
	}

	@Override
	public void delete(Integer id) {
		articleGroupRepository.deleteById(id);
		
	}

}
