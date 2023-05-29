package idv.tha101.extractp.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import idv.tha101.extractp.base.dao.BaseRepository;
import idv.tha101.extractp.web.pojo.OrderDetailVO;

@RepositoryRestResource
@Repository
public interface OrderDetailRepository extends BaseRepository<OrderDetailVO, Integer>{

	List<OrderDetailVO> findByOrderId(Integer order_id);
	
//	@Query(value = "select \r\n"
//			+ "    od.order_id,\r\n"
//			+ "    p.product_image,\r\n"
//			+ "	p.product_name,\r\n"
//			+ "    od.order_detail_quantity,\r\n"
//			+ "    od.order_product_price\r\n"
//			+ "from ORDER_DETAIL od, PRODUCT p\r\n"
//			+ "where od.product_id = p.product_id and order_id = :order_id;",nativeQuery = true)
//	List<OrderDetailVO> findOrderDetail(Integer id);
}
