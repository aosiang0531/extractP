package idv.tha101.extractp.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import idv.tha101.extractp.base.controller.BaseController;
import idv.tha101.extractp.web.pojo.OrderDetailVO;

@RestController
@RequestMapping("orderDetail")
public class OrderDetailController extends BaseController<OrderDetailVO>{

	@Override
	public List<OrderDetailVO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetailVO findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetailVO save(OrderDetailVO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

}
