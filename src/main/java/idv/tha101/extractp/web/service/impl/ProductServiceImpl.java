package idv.tha101.extractp.web.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import idv.tha101.extractp.web.dao.ProductRepository;
import idv.tha101.extractp.web.pojo.ProductVO;
import idv.tha101.extractp.web.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	public List<ProductVO> findByNameAndStatus(String keyword, String status) {
		return repository.findByProductNameLikeAndProductStatus(keyword, status);
	}

	public List<ProductVO> findByProductNameAndCategoryId(String keyword, int id) {
		return repository.findByProductNameLikeAndCategoryId(keyword, id);
	}

	public List<ProductVO> findByProductNameLike(String keyword) {
		return repository.findByProductNameLike(keyword);
	}

	// 找出0庫存商品
	public List<ProductVO> findByProductStockZero() {
		return repository.findByProductStockZero();
	}

	// 找出0售出商品
	public List<ProductVO> findByProductSoldCountZero() {
		return repository.findByProductSoldCountZero();
	}
	
	// 查詢所有「已上架」商品
	public List<ProductVO> findByStatus(String status) {
		return repository.findByProductStatus(status);
	}
	
	// 查詢所有「已上架」商品分頁版
	public Page<ProductVO> findAllOnSale(Pageable pageable) {
		return repository.findByProductStatus("上架中", pageable);
	}

	@Override
	public List<ProductVO> findAll() {
		return repository.findAll();
	}

	@Override
	public ProductVO findById(Integer id) {
		return repository.findById(id).orElseThrow();
	}
	
	@Transactional
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
