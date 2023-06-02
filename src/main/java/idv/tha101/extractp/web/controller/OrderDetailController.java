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

import com.google.gson.JsonObject;

import idv.tha101.extractp.base.controller.BaseController;
import idv.tha101.extractp.web.pojo.OrderDTO;
import idv.tha101.extractp.web.pojo.OrderDetailVO;
import idv.tha101.extractp.web.service.OrderInfoService;

@RestController
@RequestMapping("orderDetail")
public class OrderDetailController extends BaseController<OrderDetailVO> {

	@Autowired
	private OrderInfoService orderInfoService;

	@Override
	public List<OrderDetailVO> findAll() {
		return null;
	}

	@Override
	public OrderDetailVO findById(@PathVariable(value = "id") int id) {
		return orderInfoService.findByOrderDetailId(id);
	}

	@Override
	@PostMapping
	public OrderDetailVO save(OrderDetailVO vo) {
		return orderInfoService.saveOrUpdate(vo);
	}

	@Override
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value = "id") int id) {
		orderInfoService.deleteByOrderDetailId(id);

	}

	@Override
	@PutMapping("/{id}")
	public OrderDetailVO update(@RequestBody OrderDetailVO vo, @PathVariable(value = "id") int id) {
		return orderInfoService.saveOrUpdate(vo.setId(id));
	}
	
	@PutMapping("/{id}/all")
	public List<OrderDetailVO> updateAll(@RequestBody List<OrderDetailVO> reqlist, @PathVariable(value = "id") int id) {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		for(OrderDetailVO vo2 : reqlist) {
			list.add(orderInfoService.saveOrUpdate(vo2));
		}
		return list;
	}
	
	@GetMapping("/{id}")
	public List<OrderDetailVO> findByOrderId(@PathVariable(value = "id") int id) {
		return orderInfoService.findByOrderId(id);
	}
	

	@GetMapping("/{id}/total")
	public HashMap<String, Double> countTotal(@PathVariable(value = "id") int id) {
		return orderInfoService.countTotal(id);
	}
	
	@GetMapping("/{id}/info")
	public Collection<OrderDTO> findOrderInfo(@PathVariable(value = "id") int id){
		return orderInfoService.findOrderInfo(id);
	}



}
