package idv.tha101.extractp.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import idv.tha101.extractp.base.dao.BaseRepository;
import idv.tha101.extractp.web.pojo.ProductVO;

@RepositoryRestResource
@Repository
public interface ProductRepository extends BaseRepository<ProductVO, Integer> {

	// 以商品分類取得商品資料
//	List<ProductVO> selectByCategoryId(Integer category_id);

//	 以上架狀態獲取商品資料
//	List<ProductVO> findProductVOsByStatus(@Param("status") String status);
//  List<ProductVO> findByStatus(String status);
//	List<ProductVO> findBystatus(String status);
	List<ProductVO> findByProductStatus(String status);
    
    @Query("SELECT p FROM ProductVO p WHERE p.product_sold_count = 0")
    List<ProductVO> findByProductSoldCountZero();
    
    List<ProductVO> findByProductNameLike(String keyword);
    List<ProductVO> findByProductNameLikeAndCategoryId(String productName, Integer categoryId);

    
	


}
