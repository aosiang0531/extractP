package idv.tha101.extractp.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import idv.tha101.extractp.base.controller.BaseController;
import idv.tha101.extractp.web.pojo.OrderDTO;
import idv.tha101.extractp.web.pojo.OrderDetailVO;
import idv.tha101.extractp.web.pojo.OrderInfoVO;
import idv.tha101.extractp.web.service.OrderDetailService;
import idv.tha101.extractp.web.service.OrderInfoService;

@RestController
@RequestMapping("orderDetail")
public class OrderDetailController extends BaseController<OrderDetailVO> {

	@Autowired
	OrderInfoService orderInfoService;
	
	@Autowired
	OrderDetailService orderDetailService;

	@Override
	@GetMapping
	public List<OrderDetailVO> findAll() {
		return orderDetailService.findAll();
	}

	@Override
	@GetMapping("/{id}")
	public OrderDetailVO findById(@PathVariable(value = "id") int id) {
		return orderDetailService.findById(id);
	}

	@Override
	@PostMapping
	public OrderDetailVO save(@RequestBody OrderDetailVO vo) {
		System.out.println(vo);
		return orderDetailService.saveOrUpdate(vo);
	}

	@Override
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value = "id") int id) {
		orderDetailService.deleteById(id);

	}

	@Override
	@PutMapping("/{id}")
	public OrderDetailVO update(@RequestBody OrderDetailVO vo, @PathVariable(value = "id") int id) {
		return orderDetailService.saveOrUpdate(vo.setId(id));
	}
	
	@PutMapping("/{id}/all")
	public List<OrderDetailVO> updateAll(@RequestBody List<OrderDetailVO> reqlist, @PathVariable(value = "id") int id) {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		for(OrderDetailVO vo2 : reqlist) {
			list.add(orderDetailService.saveOrUpdate(vo2));
		}
		return list;
	}
	
	@GetMapping("/order/{id}")
	public List<OrderDetailVO> findByOrderId(@PathVariable(value = "id") int id) {
		return orderDetailService.findByOrderId(id);
	}
	

	@GetMapping("/{id}/total")
	public HashMap<String, Double> countTotal(@PathVariable(value = "id") int id) {
		return orderDetailService.countTotal(id);
	}
	
	@GetMapping("/{id}/info")
	public List<OrderDTO> findOrderInfo(@PathVariable(value = "id") int id){
		return orderDetailService.findOrderInfo(id);
	}

	//將訂單明細存入會員"未成立"的訂單
	@PutMapping("/{member_id}/unplaced")
	public OrderInfoVO addProductToOrder(@RequestBody OrderDetailVO vo, @PathVariable(value = "member_id") int id){
		//用member_id查未成立訂單
		OrderInfoVO info = orderInfoService.findById(id);
		vo.setOrder_id(info.getId());
		//detail 存到未成立訂單info
		orderDetailService.saveOrUpdate(vo);
		return info;		
	}
}
