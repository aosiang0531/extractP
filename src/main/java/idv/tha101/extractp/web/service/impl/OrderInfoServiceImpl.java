package idv.tha101.extractp.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.OrderDetailRepository;
import idv.tha101.extractp.web.dao.OrderInfoRepository;
import idv.tha101.extractp.web.pojo.OrderDetailVO;
import idv.tha101.extractp.web.pojo.OrderInfoVO;
import idv.tha101.extractp.web.service.OrderInfoService;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

	@Autowired
	OrderInfoRepository orderInfoRepository;

	@Autowired
	OrderDetailRepository orderDetailRepository;

	@Override
	public List<OrderInfoVO> findAll() {
		return orderInfoRepository.findAll();
	}

	@Override
	public OrderInfoVO findById(Integer id) {
		return orderInfoRepository.findById(id).orElseThrow();
	}

	@Override
	public OrderInfoVO save(OrderInfoVO vo) {
		return orderInfoRepository.save(vo);
	}

	@Override
	public void deleteById(Integer id) {
		orderInfoRepository.deleteById(id);
	}

//	@Override
//	public List<OrderInfoVO> findByMemberId(Integer member_id) {
//		// TODO Auto-generated method stub
//		return orderInfoRepository.
//	}

//	@Override
//	public List<OrderInfoVO> getPageByShippingStatus(Integer member_id, String status) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<OrderInfoVO> getPageByPaymentStatus(Integer member_id, String status) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public OrderDetailVO save(OrderDetailVO vo) {
//		return orderDetailRepository.save(vo);
//	}
//
//	@Override
//	public double countSubTotal(Integer order_info_id) {
//		// TODO Auto-generated method stub
//		orderDetailRepository.getById(order_info_id)
//		return 0;
//	}
//
//	@Override
//	public double countTotal(Integer order_id) {
//		// TODO Auto-generated method stub
//		List<OrderDetailVO> list = new arraylist<
//		double total = 
//		return 0;
//	}

//	@Override
//	public OrderDetailVO findByOrderDetailId(Integer order_info_id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
	

	

}
