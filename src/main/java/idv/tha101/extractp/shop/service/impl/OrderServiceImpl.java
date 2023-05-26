package idv.tha101.extractp.shop.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.shop.dao.OrderRepository;
import idv.tha101.extractp.shop.pojo.OrderVO;
import idv.tha101.extractp.shop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository repository;

	@Override
	public List<OrderVO> findAll() {
		return repository.findAll();
	}

	@Override
	public OrderVO findById(Integer id) {
		return repository.findById(id).orElseThrow();
	}

	@Override
	public OrderVO saveOrUpdate(OrderVO vo) {
		if (vo.getId() != null) {
			Optional<OrderVO> optionalVO = repository.findById(vo.getId());
			if (optionalVO.isPresent()) {
				OrderVO existingVO = optionalVO.get();

				Class<?> voClass = OrderVO.class;
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

				return repository.save(existingVO);
			} else {
				return null;
			}
		} else {
			return repository.save(vo);
		}
	}

	@Override
	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	@Override
	public List<OrderVO> findByOrder_status(String status) {
		return	repository.findByOrderStatus(status);
	}

}
