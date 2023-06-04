package idv.tha101.extractp.web.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import idv.tha101.extractp.base.dao.BaseRepository;
import idv.tha101.extractp.web.pojo.OrderDetailVO;
import idv.tha101.extractp.web.pojo.OrderDTO;

@RepositoryRestResource
@Repository
public interface OrderDetailRepository extends BaseRepository<OrderDetailVO, Integer>{

	
	List<OrderDetailVO> findByOrderId(Integer order_id);
	
	@Query(value = "select \r\n"
			+ "    od.order_detail_id,\r\n"
			+ "    od.product_id,\r\n"
			+ "    p.product_image,\r\n"
			+ "	   p.product_name,\r\n"
			+ "    od.order_detail_quantity,\r\n"
			+ "    od.order_product_price,\r\n"
			+ "    od.order_detail_quantity * od.order_product_price AS subtotal\r\n"
			+ "from ORDER_DETAIL od\r\n"
			+ "join PRODUCT p\r\n"
			+ "	on od.product_id = p.product_id\r\n"
			+ "where order_id = ?;",nativeQuery = true)
	Collection<OrderDTO> findOrderInfo(Integer id);


}
