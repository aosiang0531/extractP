package idv.tha101.extractp.shop.dao;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import idv.tha101.extractp.base.dao.BaseRepository;
import idv.tha101.extractp.shop.pojo.ProductVO;

@RepositoryRestResource
@Repository
public interface ProductRepository extends BaseRepository<ProductVO, Integer>{

	// 以商品分類取得商品資料
//	List<ProductVO> selectByCategoryId(Integer category_id);
}
