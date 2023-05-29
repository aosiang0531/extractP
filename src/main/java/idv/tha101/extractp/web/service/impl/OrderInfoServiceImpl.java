package idv.tha101.extractp.web.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

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
	public void deleteById(Integer id) {
		orderInfoRepository.deleteById(id);
	}

	@Override
	public OrderInfoVO saveOrUpdate(OrderInfoVO vo) {
		if (vo.getId() != null) {
			Optional<OrderInfoVO> optionalVO = orderInfoRepository.findById(vo.getId());
			if (optionalVO.isPresent()) {
				OrderInfoVO existingVO = optionalVO.get();

				Class<?> voClass = OrderInfoVO.class;
				Field[] fields = voClass.getDeclaredFields();

				for (Field field : fields) {
					field.setAccessible(true);
					try {
						Object updatedValue = field.get(vo);
						if (updatedValue != null) {
							field.set(existingVO, updatedValue);
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}

				return orderInfoRepository.save(existingVO);
			} else {
				return null;
			}
		} else {
			return orderInfoRepository.save(vo);
		}

	}

	@Override
	public List<OrderInfoVO> findByMemberId(Integer member_id) {
		return orderInfoRepository.findByMemberId(member_id);
	}

	@Override
	public List<OrderInfoVO> findPageByPaymentStatus(Integer member_id, String status) {
		return orderInfoRepository.findByMemberIdAndOrderPaymentStatus(member_id, status);
	}

	@Override
	public List<OrderInfoVO> findPageByShippingStatus(Integer member_id, String status) {
		return orderInfoRepository.findByMemberIdAndOrderShippingStatus(member_id, status);
	}

	@Override
	public List<OrderDetailVO> findByOrderId(Integer order_id) {
		return orderDetailRepository.findByOrderId(order_id);
	}

	@Override
	public OrderDetailVO saveOrUpdate(OrderDetailVO vo) {
		if (vo.getId() != null) {
			Optional<OrderDetailVO> optionalVO = orderDetailRepository.findById(vo.getId());
			if (optionalVO.isPresent()) {
				OrderDetailVO existingVO = optionalVO.get();

				Class<?> voClass = OrderDetailVO.class;
				Field[] fields = voClass.getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					try {
						Object updatedValue = field.get(vo);
						if (updatedValue != null) {
							field.set(existingVO, updatedValue);
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				return orderDetailRepository.save(existingVO);
			} else {
				return null;
			}
		} else {
			return orderDetailRepository.save(vo);
		}
	}

	@Override
	public void deleteByOrderDetailId(Integer id) {
		orderDetailRepository.deleteById(id);
	}

	@Override
	public List<Double> countSubTotal(Integer id) {
		return orderDetailRepository.findByOrderId(id).stream().mapToDouble(it->it.getQuantity()*it.getPrice()).boxed().toList();
	}

	@Override
	public double countTotal(Integer id) {
		return orderDetailRepository.findByOrderId(id).stream().mapToDouble(it->it.getQuantity()*it.getPrice()).sum();
	}

	@Override
	public OrderDetailVO findByOrderDetailId(Integer id) {
		return orderDetailRepository.findById(id).orElseThrow();
	}

//	@Override
//	public List<OrderDetailVO> findOrderDetail(Integer id) {
//		return orderDetailRepository.findOrderDetail(id);
//	}

}
