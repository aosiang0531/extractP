package idv.tha101.extractp.web.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.google.gson.JsonObject;

import idv.tha101.extractp.base.service.BaseService;
import idv.tha101.extractp.web.pojo.OrderDTO;
import idv.tha101.extractp.web.pojo.OrderDetailVO;
import idv.tha101.extractp.web.pojo.OrderInfoVO;

public interface OrderInfoService extends BaseService<OrderInfoVO>{
	
<<<<<<< HEAD
=======
	List<OrderInfoVO> findByMemberId(Integer member_id);
	
	List<OrderInfoVO> findPageByOrderStatus(Integer member_id,String status);
	
	List<OrderInfoVO> findPageByPaymentStatus(Integer member_id,String status);
	
	List<OrderInfoVO> findPageByShippingStatus(Integer member_id,String status);
	
	List<OrderDetailVO> findByOrderId(Integer order_id);
	
	OrderDetailVO findByOrderDetailId(Integer id);
	
	OrderDetailVO saveOrUpdate(OrderDetailVO vo);
	
	void deleteByOrderDetailId(Integer id);
		
	HashMap<String, Double> countTotal(Integer order_id);
	
>>>>>>> 1bbd795 (modified order and add front-end)
	Collection<OrderDTO> findOrderInfo(Integer id);
	

	List<OrderInfoVO> findAll();

	OrderInfoVO findById(Integer id);

	void deleteById(Integer id);

	OrderInfoVO saveOrUpdate(OrderInfoVO vo);

	List<OrderInfoVO> findByMemberId(Integer member_id);

	List<OrderInfoVO> findPageByPaymentStatus(Integer member_id, String status);

	List<OrderInfoVO> findPageByShippingStatus(Integer member_id, String status);

	List<OrderDetailVO> findByOrderId(Integer order_id);

	OrderDetailVO saveOrUpdate(OrderDetailVO vo);

	void deleteByOrderDetailId(Integer id);

	List<Double> countSubTotal(Integer id);

	double countTotal(Integer id);

	OrderDetailVO findByOrderDetailId(Integer id);

}