package idv.tha101.extractp.web.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.sample.pojo.SampleVO;
import idv.tha101.extractp.web.dao.ProductRepository;
import idv.tha101.extractp.web.pojo.ProductVO;
import idv.tha101.extractp.web.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Override
	public List<ProductVO> findAll() {
		return repository.findAll();
	}

	@Override
	public ProductVO findById(Integer id) {
		return repository.findById(id).orElseThrow();
	}

	@Override
	public ProductVO saveOrUpdate(ProductVO vo) {
		if (vo.getId() != null) {
			Optional<ProductVO> optionalVO = repository.findById(vo.getId());
			if (optionalVO.isPresent()) {
				ProductVO existingVO = optionalVO.get();

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

				return repository.save(existingVO);
			} else {
				return null;
			}
		} else {
			return repository.save(vo);
		}
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

}
