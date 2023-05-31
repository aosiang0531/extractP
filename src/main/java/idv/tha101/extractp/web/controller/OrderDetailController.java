package idv.tha101.extractp.web.controller;

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
import idv.tha101.extractp.web.dao.OrderDetailRepository;
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
	
	@GetMapping("/{id}")
	public List<OrderDetailVO> findByOrderId(@PathVariable(value = "id") int id) {
		return orderInfoService.findByOrderId(id);
	}
	
	@GetMapping("/{id}/subtotal")
	public List<Double> totalByOrderId(@PathVariable(value = "id") int id) {
		return orderInfoService.countSubTotal(id);
	}


	@GetMapping("/{id}/total")
	public double countTotal(@PathVariable(value = "id") int id) {
		return orderInfoService.countTotal(id);
	}
	
//	@GetMapping("/{id}/info")
//	public List<OrderDetailVO> findOrderDetail(@PathVariable(value = "id") int id){
//		return orderInfoService.findOrderDetail(id);
//	}



}
