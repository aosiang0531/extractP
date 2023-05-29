package idv.tha101.extractp.member.service.Impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.member.dao.MemberRepository;
import idv.tha101.extractp.member.pojo.MemberVO;
import idv.tha101.extractp.member.service.MemberService;


@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberrepository;

	@Override
	public List<MemberVO> findAll() {
		return memberrepository.findAll();
	}

	@Override
	public MemberVO findById(Integer id) {
		return memberrepository.findById(id).orElseThrow();
	}

	@Override
	public MemberVO saveOrUpdate(MemberVO vo) {
		if (vo.getId() != null) {
			Optional<MemberVO> optionalVO = memberrepository.findById(vo.getId());
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

				return memberrepository.save(existingVO);
			} else {
				return null;
			}
		} else {
			return memberrepository.save(vo);
		}
	}

	@Override
	public void deleteById(Integer id) {
		memberrepository.deleteById(id);
	}


}
