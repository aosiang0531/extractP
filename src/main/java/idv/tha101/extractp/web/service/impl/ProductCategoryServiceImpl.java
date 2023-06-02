package idv.tha101.extractp.web.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.sample.pojo.SampleVO;
import idv.tha101.extractp.web.dao.ProductCategoryRepository;
import idv.tha101.extractp.web.pojo.ProductCategoryVO;
import idv.tha101.extractp.web.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{ 

	@Autowired
	private ProductCategoryRepository repository;

	@Override
	public List<ProductCategoryVO> findAll() {
		return repository.findAll();
	}

	@Override
	public ProductCategoryVO findById(Integer id) {
		return repository.findById(id).orElseThrow();
	}

	@Override
	public ProductCategoryVO saveOrUpdate(ProductCategoryVO vo) {
		if (vo.getId() != null) {
			Optional<ProductCategoryVO> optionalVO = repository.findById(vo.getId());
			if (optionalVO.isPresent()) {
				ProductCategoryVO existingVO = optionalVO.get();

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
