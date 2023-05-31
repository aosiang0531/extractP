package idv.tha101.extractp.web.dao;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import idv.tha101.extractp.base.dao.BaseRepository;
import idv.tha101.extractp.web.pojo.OrderInfoVO;

@RepositoryRestResource
@Repository
public interface OrderInfoRepository extends BaseRepository<OrderInfoVO, Integer>{

	List<OrderInfoVO> findByMemberId(Integer member_id);
	
	List<OrderInfoVO> findByMemberIdAndOrderPaymentStatus(Integer member_id,String status);
	
	List<OrderInfoVO> findByMemberIdAndOrderShippingStatus(Integer member_id,String status);
	
	
//	//以訂單狀態獲取訂單
//	List<OrderInfoVO> findOrderInfoVOsByOrderStatus(@Param("status") String status);
//	
//	//以會員編號獲取所有訂單
//	List<OrderInfoVO> findOrderInfoVOsByMemberId(Integer memberId);
}
