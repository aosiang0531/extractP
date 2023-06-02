package idv.tha101.extractp.web.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.MemberRepository;
import idv.tha101.extractp.web.pojo.MemberVO;
import idv.tha101.extractp.web.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public List<MemberVO> findAll() {
		return memberRepository.findAll();
	}

	@Override
	public MemberVO findById(Integer id) {
		return memberRepository.findById(id).orElseThrow();
	}

	@Override
	public MemberVO saveOrUpdate(MemberVO vo) {
		if (vo.getId() != null) {
			Optional<MemberVO> optionalVO = memberRepository.findById(vo.getId());
			if (optionalVO.isPresent()) {
				MemberVO existingVO = optionalVO.get();

				Class<?> voClass = MemberVO.class;
				Field[] fields = voClass.getDeclaredFields();

				for (Field field : fields) {
					field.setAccessible(true);
					try {
						Object updatedValue = field.get(vo);
						if (updatedValue != null) {
							field.set(existingVO, updatedValue);
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}

				return memberRepository.save(existingVO);
			} else {
				return null;
			}
		} else {
			return memberRepository.save(vo);
		}
	}

	@Override
	public void deleteById(Integer id) {
		memberRepository.deleteById(id);
	}

	@Override
	public MemberVO register(MemberVO memberVO) {
		if (memberVO.getId() == null) {
			if (memberRepository.existsByEmail(memberVO.getEmail())) {
				return null;
			} else {
				return memberRepository.save(memberVO);
			}
		} else {
			return null;
		}

	}

//	@Override
//	public String sendSimpleMail(MemberEmailDTO details) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	

}
