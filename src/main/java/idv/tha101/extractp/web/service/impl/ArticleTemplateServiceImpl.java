package idv.tha101.extractp.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.ArticleTemplateRepository;
import idv.tha101.extractp.web.pojo.ArticleTemplateVO;
import idv.tha101.extractp.web.service.ArticleTemplateService;

@Service
public class ArticleTemplateServiceImpl implements ArticleTemplateService{

	@Autowired
	private ArticleTemplateRepository articleTemplateRepository;
	
	@Override
	public List<ArticleTemplateVO> findAll() {
		return articleTemplateRepository.findAll();
	}

	@Override
	public ArticleTemplateVO findById(Integer id) {
		return articleTemplateRepository.findById(id).orElseThrow();
	}

	@Override
	public ArticleTemplateVO save(ArticleTemplateVO vo) {
		return articleTemplateRepository.save(vo);
	}

	@Override
	public void delete(Integer id) {
		articleTemplateRepository.deleteById(id);
		
	}

}
