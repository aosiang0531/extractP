package idv.tha101.extractp.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import idv.tha101.extractp.base.controller.BaseController;
import idv.tha101.extractp.shop.pojo.OrderVO;
import idv.tha101.extractp.shop.pojo.ProductVO;
import idv.tha101.extractp.shop.service.OrderService;

@RestController
@RequestMapping("order")
public class OrderController extends BaseController<OrderVO> {
	
	@Autowired
	private OrderService service;
	
	@Override
	@GetMapping
	public List<OrderVO> findAll() {
		return	service.findAll();
	}

	@Override
	@GetMapping("/{id}")
	public OrderVO findById(@PathVariable(value = "id") int id) {
		System.out.println("訂單編號為：" + id);
		return service.findById(id);
	}

	@Override
	@PostMapping
	public OrderVO save(@RequestBody OrderVO product) {

	return service.saveOrUpdate(product);
	}
	
	@Override
	@PutMapping("/{id}")
	public OrderVO update(@RequestBody OrderVO vo, @PathVariable(value = "id") int id) {
		return service.saveOrUpdate(vo.setId(id));
	}
	
	@Override
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value = "id")int id) {
		service.deleteById(id);
	}
	
	//透過get給訂單狀態參數的方式，獲取購物車/訂單
	@GetMapping("/")
	public List<OrderVO> findOrderByStatus(@RequestParam("order_status") String status){
		return service.findByOrder_status(status);
	}
	



}
