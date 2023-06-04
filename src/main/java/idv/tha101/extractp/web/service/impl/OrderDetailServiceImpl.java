package idv.tha101.extractp.web.service.impl;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.OrderDetailRepository;
import idv.tha101.extractp.web.pojo.OrderDTO;
import idv.tha101.extractp.web.pojo.OrderDetailVO;
import idv.tha101.extractp.web.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl  implements OrderDetailService{

	@Autowired
	OrderDetailRepository orderDetailRepository;
	
	@Override
	public List<OrderDetailVO> findAll() {
		return orderDetailRepository.findAll();
	}

	@Override
	public OrderDetailVO findById(Integer id) {
		return orderDetailRepository.findById(id).orElseThrow();
	}

	@Override
	public OrderDetailVO saveOrUpdate(OrderDetailVO vo) {
		if (vo.getId() != null) {
			Optional<OrderDetailVO> optionalVO =orderDetailRepository.findById(vo.getId());
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
	public void deleteById(Integer id) {
		orderDetailRepository.deleteById(id);
		
	}

	@Override
	public List<OrderDetailVO> findByOrderId(Integer order_id) {
		return orderDetailRepository.findByOrderId(order_id);
	}

	@Override
	public HashMap<String, Double> countTotal(Integer order_id) {
		double total = orderDetailRepository.findByOrderId(order_id).stream().mapToDouble(it->it.getQuantity()*it.getPrice()).sum();
		HashMap<String, Double> map = new HashMap<>();
		map.put("total",total);
		return map;
	}

	@Override
	public Collection<OrderDTO> findOrderInfo(Integer id) {
		return orderDetailRepository.findOrderInfo(id);
	}
	
}
