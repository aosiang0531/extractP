package idv.tha101.extractp.web.service;

import java.util.List;

import idv.tha101.extractp.base.service.BaseService;
import idv.tha101.extractp.web.pojo.OrderInfoVO;

public interface OrderInfoService extends BaseService<OrderInfoVO>{
	
	List<OrderInfoVO> findPageByOrderStatus(Integer member_id,String status);
	
	List<OrderInfoVO> findByMemberId(Integer member_id);

	List<OrderInfoVO> findPageByPaymentStatus(Integer member_id, String status);

	List<OrderInfoVO> findPageByShippingStatus(Integer member_id, String status);


}