package idv.tha101.extractp.web.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.ArticleRepository;
import idv.tha101.extractp.web.dao.MemberArticleFavRepository;
import idv.tha101.extractp.web.pojo.ArticleVO;
import idv.tha101.extractp.web.pojo.MemberArticleFavVO;
import idv.tha101.extractp.web.pojo.MemberArticleFavVO.FavPk;
import idv.tha101.extractp.web.service.ArticleReportService;
import idv.tha101.extractp.web.service.MemberArticleFavService;

@Service
public class MemberArticleFavServiceImpl implements MemberArticleFavService {

	@Autowired
	private MemberArticleFavRepository memberArticleFavRepository;
	
	@Autowired
	private ArticleRepository articleRepository;

	@Override
	public List<MemberArticleFavVO> findAll() {
		return memberArticleFavRepository.findAll();
	}

	@Override
	public MemberArticleFavVO findById(Integer id) {
//		FavPk pk = new FavPk();
//		pk.setMember_id(id);
//		return memberArticleFavRepository.findById(pk).orElseThrow();
		return null;
	}

	@Override
	public MemberArticleFavVO saveOrUpdate(MemberArticleFavVO vo) {
		return null;
	}

	@Override
	public void deleteById(Integer id) {
	}

	@Override
	public void deleteFav(FavPk pk) {
		int articleId = pk.getArticle_id();
		ArticleVO article = articleRepository.getById(articleId);
		article.setMember_article_fav_number(article.getMember_article_fav_number() - 1);
		memberArticleFavRepository.deleteById(pk);
	}

	@Override
	public List<MemberArticleFavVO> findFavByMemberId(Integer id) {
		return memberArticleFavRepository.findByPkMemberId(id);
	}

}
