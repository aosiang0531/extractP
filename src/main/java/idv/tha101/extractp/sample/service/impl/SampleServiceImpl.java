package idv.tha101.extractp.sample.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.sample.dao.SampleRepository;
import idv.tha101.extractp.sample.pojo.SampleVO;
import idv.tha101.extractp.sample.service.SampleService;

@Service
public class SampleServiceImpl implements SampleService {

	@Autowired
	private SampleRepository sampleRepositry;

	@Override
	public List<SampleVO> findAll() {
		return sampleRepositry.findAll();
	}

	@Override
	public SampleVO findById(Integer id) {
		return sampleRepositry.findById(id).orElseThrow();
	}

	@Override
	public SampleVO saveOrUpdate(SampleVO vo) {
		if (vo.getId() != null) {
			Optional<SampleVO> optionalVO = sampleRepositry.findById(vo.getId());
			if (optionalVO.isPresent()) {
				SampleVO existingVO = optionalVO.get();

				Class<?> voClass = SampleVO.class;
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

				return sampleRepositry.save(existingVO);
			} else {
				return null;
			}
		} else {
			return sampleRepositry.save(vo);
		}
	}

	@Override
	public void deleteById(Integer id) {
		sampleRepositry.deleteById(id);
	}

}
