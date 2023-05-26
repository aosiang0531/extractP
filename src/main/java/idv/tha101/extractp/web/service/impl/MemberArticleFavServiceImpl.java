package idv.tha101.extractp.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.MemberArticleFavRepository;
import idv.tha101.extractp.web.pojo.MemberArticleFavVO;
import idv.tha101.extractp.web.service.MemberArticleFavService;

@Service
public class MemberArticleFavServiceImpl implements MemberArticleFavService{

	@Autowired
	private MemberArticleFavRepository memberArticleFavRepository;
	
	@Override
	public List<MemberArticleFavVO> findAll() {
		return memberArticleFavRepository.findAll();
	}

	@Override
	public MemberArticleFavVO findById(Integer id) {
		return memberArticleFavRepository.findById(id).orElseThrow();
	}

	@Override
	public MemberArticleFavVO save(MemberArticleFavVO vo) {
		return memberArticleFavRepository.save(vo);
	}

	@Override
	public void delete(Integer id) {
		memberArticleFavRepository.deleteById(id);
		
	}

}
