package idv.tha101.extractp.shop.service;

import java.util.List;

import idv.tha101.extractp.shop.pojo.MemberVO;

public interface MemberService {

	List<MemberVO> findAll();

	MemberVO findById(Integer id);

	MemberVO saveOrUpdate(MemberVO vo);

	void deleteById(Integer id);

}