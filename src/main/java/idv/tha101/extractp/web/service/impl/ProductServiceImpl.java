package idv.tha101.extractp.web.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.ProductRepository;
import idv.tha101.extractp.web.pojo.ProductVO;
import idv.tha101.extractp.web.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;
	
	
	public List<ProductVO> findByProductNameAndCategoryId(String keyword,int id){
		return repository.findByProductNameLikeAndCategoryId(keyword,id);
	}
	public List<ProductVO> findByProductNameLike(String keyword){
		return repository.findByProductNameLike(keyword);
		}
	
	//找出0庫存商品
	public List<ProductVO> findByProductSoldCountZero() {
		return repository.findByProductSoldCountZero();
	}

	public List<ProductVO> findByStatus(String status) {
		return repository.findByProductStatus(status);
	}

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

				Class<?> voClass = ProductVO.class;
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
