package idv.tha101.extractp.web.service;

import java.util.Collection;
import java.util.List;

import idv.tha101.extractp.base.service.BaseService;
import idv.tha101.extractp.web.pojo.OrderDTO;
import idv.tha101.extractp.web.pojo.OrderInfoVO;

public interface OrderInfoService extends BaseService<OrderInfoVO>{
	
	List<OrderInfoVO> findPageByOrderStatus(Integer member_id,String status);
	
	Collection<OrderDTO> findOrderInfo(Integer id);

	List<OrderInfoVO> findAll();

	OrderInfoVO findById(Integer id);

	void deleteById(Integer id);

	OrderInfoVO saveOrUpdate(OrderInfoVO vo);

	List<OrderInfoVO> findByMemberId(Integer member_id);

	List<OrderInfoVO> findPageByPaymentStatus(Integer member_id, String status);

	List<OrderInfoVO> findPageByShippingStatus(Integer member_id, String status);

	void deleteByOrderDetailId(Integer id);

	List<Double> countSubTotal(Integer id);

	double countTotal(Integer id);

}