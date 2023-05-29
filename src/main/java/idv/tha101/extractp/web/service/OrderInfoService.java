package idv.tha101.extractp.web.service;

import java.util.List;

import idv.tha101.extractp.base.service.BaseService;
import idv.tha101.extractp.web.pojo.OrderDetailVO;
import idv.tha101.extractp.web.pojo.OrderInfoVO;

public interface OrderInfoService extends BaseService<OrderInfoVO>{
	
	List<OrderInfoVO> findByMemberId(Integer member_id);
	
	List<OrderInfoVO> findPageByPaymentStatus(Integer member_id,String status);
	
	List<OrderInfoVO> findPageByShippingStatus(Integer member_id,String status);
	
	List<OrderDetailVO> findByOrderId(Integer order_id);
	
	OrderDetailVO findByOrderDetailId(Integer id);
	
	OrderDetailVO saveOrUpdate(OrderDetailVO vo);
	
	void deleteByOrderDetailId(Integer id);
	
	List<Double> countSubTotal(Integer order_id);
	
	double countTotal(Integer order_id);
	
//	List<OrderDetailVO> findOrderDetail(Integer id);

}
