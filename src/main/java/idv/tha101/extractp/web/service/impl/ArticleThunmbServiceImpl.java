package idv.tha101.extractp.web.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.ArticleThunmbRepository;
import idv.tha101.extractp.web.pojo.ArticleThunmbVO;
import idv.tha101.extractp.web.service.ArticleThunmbService;

@Service
public class ArticleThunmbServiceImpl implements ArticleThunmbService {

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
	public ArticleThunmbVO saveOrUpdate(ArticleThunmbVO vo) {
// TODO
		return null;
	}

	@Override
	public void deleteById(Integer id) {
		articleThunmbRepository.deleteById(id);
	}

}
