package idv.tha101.extractp.web.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.MemberArticleFavRepository;
import idv.tha101.extractp.web.pojo.MemberArticleFavVO;
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
		return memberArticleFavRepository.findById(id).orElseThrow();
	}

	@Override
	public MemberArticleFavVO saveOrUpdate(MemberArticleFavVO vo) {
// TODO
		return null;
	}

	@Override
	public void deleteById(Integer id) {
		memberArticleFavRepository.deleteById(id);

	}

}
