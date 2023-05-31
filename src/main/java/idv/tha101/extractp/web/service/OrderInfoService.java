package idv.tha101.extractp.web.service;

import java.util.List;

import idv.tha101.extractp.web.pojo.OrderDetailVO;
import idv.tha101.extractp.web.pojo.OrderInfoVO;

public interface OrderInfoService {

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