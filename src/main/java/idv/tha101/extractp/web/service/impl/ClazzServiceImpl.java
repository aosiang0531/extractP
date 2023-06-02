package idv.tha101.extractp.web.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.ClazzRepository;
import idv.tha101.extractp.web.pojo.ClazzVO;
import idv.tha101.extractp.web.service.ClazzService;




@Service
public class ClazzServiceImpl implements ClazzService {
	@Autowired
	private ClazzRepository clazzRepositry;

	@Override
	public List<ClazzVO> findAll() {
		return clazzRepositry.findAll();
	}

	@Override
	public ClazzVO findById(Integer id) {
		return clazzRepositry.findById(id).orElseThrow();
	}

	@Override
	public ClazzVO saveOrUpdate(ClazzVO vo) {
		if (vo.getId() != null) {
			Optional<ClazzVO> optionalVO = clazzRepositry.findById(vo.getId());
			if (optionalVO.isPresent()) {
				ClazzVO existingVO = optionalVO.get();

				Class<?> voClass = ClazzVO.class;
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

				return clazzRepositry.save(existingVO);
			} else {
				return null;
			}
		} else {
			return clazzRepositry.save(vo);
		}
	}

	@Override
	public void deleteById(Integer id) {
		clazzRepositry.deleteById(id);
	}

	}
