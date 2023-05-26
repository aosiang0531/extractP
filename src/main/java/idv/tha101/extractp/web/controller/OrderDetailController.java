package idv.tha101.extractp.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import idv.tha101.extractp.base.controller.BaseController;
import idv.tha101.extractp.web.pojo.OrderDetailVO;
import idv.tha101.extractp.web.service.OrderInfoService;

@RestController
@RequestMapping("orderDetail")
public class OrderDetailController extends BaseController<OrderDetailVO>{
	
	@Autowired
	private OrderInfoService orderInfoService;
	
	@Override
	@GetMapping
	public List<OrderDetailVO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GetMapping("/{id}")
	public OrderDetailVO findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@PostMapping
	public OrderDetailVO save(OrderDetailVO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@DeleteMapping
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

}
