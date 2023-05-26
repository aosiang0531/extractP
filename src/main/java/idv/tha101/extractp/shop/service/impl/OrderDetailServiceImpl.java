package idv.tha101.extractp.shop.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.sample.pojo.SampleVO;
import idv.tha101.extractp.shop.dao.OrderDetailRepository;
import idv.tha101.extractp.shop.pojo.OrderDetailVO;
import idv.tha101.extractp.shop.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailRepository repository;

	@Override
	public List<OrderDetailVO> findAll() {
		return repository.findAll();
	}

	@Override
	public OrderDetailVO findById(Integer id) {
		return repository.findById(id).orElseThrow();
	}

	@Override
	public OrderDetailVO saveOrUpdate(OrderDetailVO vo) {
		if (vo.getId() != null) {
			Optional<OrderDetailVO> optionalVO = repository.findById(vo.getId());
			if (optionalVO.isPresent()) {
				OrderDetailVO existingVO = optionalVO.get();

				Class<?> voClass = SampleVO.class;
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



}
