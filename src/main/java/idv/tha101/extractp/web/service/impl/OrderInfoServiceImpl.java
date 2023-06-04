package idv.tha101.extractp.web.service.impl;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.OrderInfoRepository;
import idv.tha101.extractp.web.pojo.OrderInfoVO;
import idv.tha101.extractp.web.service.OrderInfoService;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

	@Autowired
	OrderInfoRepository orderInfoRepository;

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
	public List<OrderInfoVO> findByMemberIdAndOrderStatus(Integer member_id, String status) {
 		return orderInfoRepository.findByMemberIdAndOrderStatus(member_id, status);
	}

	@Override
	public List<OrderInfoVO> findByMemberIdAndPaymentStatus(Integer member_id, String status) {
		return orderInfoRepository.findByMemberIdAndOrderPaymentStatus(member_id, status);
	}

	@Override
	public List<OrderInfoVO> findByMemberIdAndShippingStatus(Integer member_id, String status) {
		return orderInfoRepository.findByMemberIdAndOrderShippingStatus(member_id, status);
	}

	@Override
	public List<OrderInfoVO> findByOrderStatus() {
		return orderInfoRepository.findByOrderStatus();
	}

	@Override
	public List<OrderInfoVO> findByPaymentStatus(String status) {
		List<OrderInfoVO> list = orderInfoRepository.findByOrderStatus();
		if("未付款".equals(status)) {
			return list.stream().collect(Collectors.toList());
		}
		return list;
	}

	@Override
	public List<OrderInfoVO> findByShippingStatus(String status) {
		List<OrderInfoVO> list = orderInfoRepository.findByOrderStatus();
		if("待出貨".equals(status)) {
			return list.stream().collect(Collectors.toList());
		}else if(("已出貨").equals(status)) {
			return list.stream().collect(Collectors.toList());
		}else if(("已完成").equals(status)) {
			return list.stream().collect(Collectors.toList());
		}
			return list;
	}

	@Override
	public OrderInfoVO addCart(Integer member_id) {
		if(orderInfoRepository.addCart(member_id) == null) {
			OrderInfoVO orderInfoVO = new OrderInfoVO();
			orderInfoVO.setMember_id(member_id);
			orderInfoVO.setStatus("未成立");
			return orderInfoRepository.save(orderInfoVO);
		}else {
			return orderInfoRepository.addCart(member_id);
		}
	}

	@Override
	public List<OrderInfoVO> pickDate(Integer member_id,Timestamp date1,Timestamp date2) {
		List<OrderInfoVO> list = orderInfoRepository.findByMemberId(member_id).stream()
							.filter(e -> e.getCreated_date().after(date1) && 
									e.getCreated_date().before(date2)).collect(Collectors.toList());
		return list;
	}

	


		
}
