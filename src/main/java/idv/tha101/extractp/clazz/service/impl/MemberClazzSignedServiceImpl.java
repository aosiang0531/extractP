package idv.tha101.extractp.clazz.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.clazz.dao.MemberClazzSignedRepository;
import idv.tha101.extractp.clazz.pojo.MemberClazzSignedVO;
import idv.tha101.extractp.clazz.service.MemberClazzSignedService;


@Service
public class MemberClazzSignedServiceImpl implements MemberClazzSignedService {
	@Autowired
	private MemberClazzSignedRepository memberClazzSignedRepositry;

	@Override
	public List<MemberClazzSignedVO> findAll() {
		return memberClazzSignedRepositry.findAll();
	}

	@Override
	public MemberClazzSignedVO findById(Integer id) {
		return memberClazzSignedRepositry.findById(id).orElseThrow();
	}

	@Override
	public MemberClazzSignedVO saveOrUpdate(MemberClazzSignedVO vo) {
		if (vo.getId() != null) {
			Optional<MemberClazzSignedVO> optionalVO = memberClazzSignedRepositry.findById(vo.getId());
			if (optionalVO.isPresent()) {
				MemberClazzSignedVO existingVO = optionalVO.get();

				Class<?> voClass = MemberClazzSignedVO.class;
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

				return memberClazzSignedRepositry.save(existingVO);
			} else {
				return null;
			}
		} else {
			return memberClazzSignedRepositry.save(vo);
		}
	}

	@Override
	public void deleteById(Integer id) {
		memberClazzSignedRepositry.deleteById(id);
	}

}
