package idv.tha101.extractp.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.MemberArticleFavRepository;
import idv.tha101.extractp.web.pojo.MemberArticleFavVO;
import idv.tha101.extractp.web.pojo.MemberArticleFavVO.FavPk;
import idv.tha101.extractp.web.service.MemberArticleFavService;

@Service
public class MemberArticleFavServiceImpl implements MemberArticleFavService {

	@Autowired
	private MemberArticleFavRepository memberArticleFavRepository;

	@Override
	public List<MemberArticleFavVO> findAll() {
		return memberArticleFavRepository.findAll();
	}

	@Override
	public MemberArticleFavVO findById(Integer id) {
		FavPk pk = new FavPk();
		pk.setMember_id(id);
		return memberArticleFavRepository.findById(pk).orElseThrow();
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
		pk.getArticle_id();
		memberArticleFavRepository.deleteById(pk);
	}

}
