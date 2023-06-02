package idv.tha101.extractp.web.dao;

import java.util.List;

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
	
	@Query(value = "select *\r\n"
			+ "from ORDER_INFO\r\n"
			+ "where member_id = ? and order_status = ?",nativeQuery = true)
	List<OrderInfoVO> findByMemberIdAndOrderStatus(Integer member_id,String status);
	
	@Query(value = "select *\r\n"
			+ "from ORDER_INFO\r\n"
			+ "where member_id = ? and order_status = \"已成立\" and order_payment_status = ?",nativeQuery = true)
	List<OrderInfoVO> findByMemberIdAndOrderPaymentStatus(Integer member_id,String status);
	
	@Query(value = "select *\r\n"
			+ "from ORDER_INFO\r\n"
			+ "where member_id = ? and order_status = \"已成立\" and order_shipping_status = ?",nativeQuery = true)
	List<OrderInfoVO> findByMemberIdAndOrderShippingStatus(Integer member_id,String status);
	
	
//	//以訂單狀態獲取訂單
//	List<OrderInfoVO> findOrderInfoVOsByOrderStatus(@Param("status") String status);
//	
//	//以會員編號獲取所有訂單
//	List<OrderInfoVO> findOrderInfoVOsByMemberId(Integer memberId);
}
