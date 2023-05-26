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
import org.springframework.web.bind.annotation.RestController;

import idv.tha101.extractp.base.controller.BaseController;
import idv.tha101.extractp.shop.pojo.OrderDetailVO;
import idv.tha101.extractp.shop.service.OrderDetailService;

@RestController
@RequestMapping("orderDetail")
public class OrderDetailController extends BaseController<OrderDetailVO> {
	
	@Autowired
	private OrderDetailService service;
	
	@Override
	@GetMapping
	public List<OrderDetailVO> findAll() {
		return	service.findAll();
	}

	@Override
	@GetMapping("/{id}")
	public OrderDetailVO findById(@PathVariable(value = "id") int id) {
		return service.findById(id);
	}

	@Override
	@PostMapping
	public OrderDetailVO save(@RequestBody OrderDetailVO product) {

	return service.saveOrUpdate(product);
	}
	
	@Override
	@PutMapping("/{id}")
	public OrderDetailVO update(@RequestBody OrderDetailVO vo, @PathVariable(value = "id") int id) {
		return service.saveOrUpdate(vo.setId(id));
	}
	
	@Override
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value = "id")int id) {
		service.deleteById(id);
	}


}
