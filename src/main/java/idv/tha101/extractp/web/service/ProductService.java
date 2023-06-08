package idv.tha101.extractp.web.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import idv.tha101.extractp.base.service.BaseService;
import idv.tha101.extractp.web.pojo.ProductVO;

public interface ProductService extends BaseService<ProductVO>{
	List<ProductVO> findByNameAndStatus(String keyword, String status);
	List<ProductVO> findByProductNameAndCategoryId(String keyword,int id);
	List<ProductVO> findByProductNameLike(String keyword);
	List<ProductVO> findByProductStockZero();
	
	// 查詢所有「已上架」商品
	List<ProductVO> findByStatus(String status);
	// 查詢所有「已上架」商品-分頁版
	Page<ProductVO> findAllOnSale(Pageable pageable);
	// 查詢所有商品-分頁版
	Page<ProductVO> findAll(Pageable pageable);
	
	

}