package idv.tha101.extractp.web.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.MemberEventSignedRepository;
import idv.tha101.extractp.web.pojo.MemberEventSignedVO;
import idv.tha101.extractp.web.service.MemberEventSignedService;




@Service
public class MemberEventSignedServiceImpl implements MemberEventSignedService {
	@Autowired
	private MemberEventSignedRepository memberEventSignedRepositry;

	@Override
	public List<MemberEventSignedVO> findAll() {
		return memberEventSignedRepositry.findAll();
	}

	@Override
	public MemberEventSignedVO findById(Integer id) {
		return memberEventSignedRepositry.findById(id).orElseThrow();
	}

	@Override
	public MemberEventSignedVO saveOrUpdate(MemberEventSignedVO vo) {
		if (vo.getId() != null) {
			Optional<MemberEventSignedVO> optionalVO = memberEventSignedRepositry.findById(vo.getId());
			if (optionalVO.isPresent()) {
				MemberEventSignedVO existingVO = optionalVO.get();

				Class<?> voClass = MemberEventSignedVO.class;
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

				return memberEventSignedRepositry.save(existingVO);
			} else {
				return null;
			}
		} else {
			return memberEventSignedRepositry.save(vo);
		}
	}

	@Override
	public void deleteById(Integer id) {
		memberEventSignedRepositry.deleteById(id);
	}

}
