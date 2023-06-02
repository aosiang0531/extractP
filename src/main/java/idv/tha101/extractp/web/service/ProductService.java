package idv.tha101.extractp.web.service;

import java.util.List;

import idv.tha101.extractp.base.service.BaseService;
import idv.tha101.extractp.web.pojo.ProductVO;

public interface ProductService extends BaseService<ProductVO>{
	
	List<ProductVO> findByProductSoldCountZero();
	List<ProductVO> findByStatus(String status);

}