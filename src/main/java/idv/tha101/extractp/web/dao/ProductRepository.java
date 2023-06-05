package idv.tha101.extractp.web.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	// 以上架狀態獲取商品資料
	List<ProductVO> findByProductStatus(String status);
	
	// 以上架狀態獲取商品資料(分頁版本)
	Page<ProductVO> findByProductStatus(String status, Pageable pageable);
    
	// 獲取已售完商品
    @Query("SELECT p FROM ProductVO p WHERE p.product_stock = 0")
    List<ProductVO> findByProductStockZero();
    
    // 獲取0售出商品
    @Query("SELECT p FROM ProductVO p WHERE p.product_sold_count = 0")
    List<ProductVO> findByProductSoldCountZero();
    
    // 以關鍵字搜尋所有商品
    List<ProductVO> findByProductNameLike(String keyword);
    
    // 以關鍵字及分類編號搜尋所有商品
    List<ProductVO> findByProductNameLikeAndCategoryId(String productName, Integer categoryId);
    
    // 以關鍵字及上架狀態搜尋商品
    List<ProductVO> findByProductNameLikeAndProductStatus(String productName, String productStatus);
    
	


}
