package idv.tha101.extractp.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import idv.tha101.extractp.base.controller.BaseController;
import idv.tha101.extractp.web.pojo.OrderInfoVO;

@RestController
@RequestMapping("orderInfo")
public class OrderInfoController extends BaseController<OrderInfoVO> {

	@Override
	public List<OrderInfoVO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderInfoVO findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderInfoVO save(OrderInfoVO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}



}
