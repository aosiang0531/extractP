package idv.tha101.extractp.web.dao;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import idv.tha101.extractp.base.dao.BaseRepository;
import idv.tha101.extractp.web.pojo.OrderInfoVO;

@RepositoryRestResource
@Repository
public interface OrderInfoRepository extends BaseRepository<OrderInfoVO, Integer>{

	@Query(value = "select *\r\n"
			+ "from ORDER_INFO\r\n"
			+ "where member_id = ?;",nativeQuery = true)
	List<OrderInfoVO> findByMemberId(Integer member_id);
	
	//會員只能有一筆未成立訂單
	@Query(value = "select *\r\n"
			+ "from ORDER_INFO\r\n"
			+ "where member_id = ? and order_status = \"未成立\";",nativeQuery = true)
	OrderInfoVO addCart(Integer member_id);
	
	//會員-全部訂單(含未成立)
	@Query(value = "select *\r\n"
			+ "from ORDER_INFO\r\n"
			+ "where member_id = ? and order_status = ?;",nativeQuery = true)
	List<OrderInfoVO> findByMemberIdAndOrderStatus(Integer member_id,String status);
	
	//會員-已成立訂單的付款狀態
	@Query(value = "select *\r\n"
			+ "from ORDER_INFO\r\n"
			+ "where member_id = ? and order_status = \"已成立\" and order_payment_status = ?;",nativeQuery = true)
	List<OrderInfoVO> findByMemberIdAndOrderPaymentStatus(Integer member_id,String status);
	
	//會員-已成立訂單的物流狀態
	@Query(value = "select *\r\n"
			+ "from ORDER_INFO\r\n"
			+ "where member_id = ? and order_status = \"已成立\" and order_shipping_status = ?;",nativeQuery = true)
	List<OrderInfoVO> findByMemberIdAndOrderShippingStatus(Integer member_id,String status);
	
	//訂單管理-全部訂單的狀態
	@Query(value = "select *\r\n"
			+ "from ORDER_INFO\r\n"
			+ "where order_status = \"已成立\";",nativeQuery = true)
	List<OrderInfoVO> findByOrderStatus();
	
	//訂單管理-已成立訂單的付款狀態
	@Query(value = "select *\r\n"
			+ "from ORDER_INFO\r\n"
			+ "where order_status = \"已成立\" and order_payment_status = ?;",nativeQuery = true)	
	List<OrderInfoVO> findByPaymentStatus(String status);
		
	//訂單管理-已成立訂單的物流狀態
	@Query(value = "select *\r\n"
			+ "from ORDER_INFO\r\n"
			+ "where order_status = \"已成立\" and order_shipping_status = ?;",nativeQuery = true)
	List<OrderInfoVO> findByShippingStatus(String status);
	
	
//	//以訂單狀態獲取訂單
//	List<OrderInfoVO> findOrderInfoVOsByOrderStatus(@Param("status") String status);
//	
//	//以會員編號獲取所有訂單
//	List<OrderInfoVO> findOrderInfoVOsByMemberId(Integer memberId);
}
