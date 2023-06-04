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
import idv.tha101.extractp.web.pojo.OrderInfoVO;
import idv.tha101.extractp.web.service.OrderInfoService;

@RestController
@RequestMapping("orderInfo")
public class OrderInfoController extends BaseController<OrderInfoVO> {

	@Autowired
	OrderInfoService orderInfoService;

	@Override
	@GetMapping
	public List<OrderInfoVO> findAll() {
		return orderInfoService.findAll();
	}

	@Override
	@GetMapping("/{id}")
	public OrderInfoVO findById(@PathVariable(value = "id") int id) {
		return orderInfoService.findById(id);
	}

	@Override
	@PostMapping
	public OrderInfoVO save(@RequestBody OrderInfoVO vo) {
		return orderInfoService.saveOrUpdate(vo);
	}

	@Override
	@PutMapping("/{id}")
	public OrderInfoVO update(@RequestBody OrderInfoVO vo, @PathVariable(value = "id") int id) {
		return orderInfoService.saveOrUpdate(vo.setId(id));
	}

	@Override
	@DeleteMapping
	public void deleteById(@PathVariable(value = "id") int id) {
		orderInfoService.deleteById(id);
	}

	@GetMapping("/{member_id}/member")
	public List<OrderInfoVO> findMemberList(@PathVariable(value = "member_id") int id) {
		return orderInfoService.findByMemberId(id);
	}
	
	@GetMapping("/{member_id}/orderstatus/{order_status}")
	public List<OrderInfoVO> findByMemberOrderStatus(
			@PathVariable(value = "member_id") int id, 
			@PathVariable(value = "order_status") String orderstatus) {
		return orderInfoService.findByMemberIdAndOrderStatus(id, orderstatus);
	}

	@GetMapping("/{member_id}/paymentstatus/{payment_status}")
	public List<OrderInfoVO> findByMemberPaymentStatus(
			@PathVariable(value = "member_id") int id, 
			@PathVariable(value = "payment_status") String paymentStatus) {
		return orderInfoService.findByMemberIdAndPaymentStatus(id, paymentStatus);
	}
	
	@GetMapping("/{member_id}/shippingstatus/{shipping_status}")
	public List<OrderInfoVO> findByMemberShippingStatus(
			@PathVariable(value = "member_id") int id, 
			@PathVariable(value = "shipping_status") String shippingStatus) {
		return orderInfoService.findByMemberIdAndShippingStatus(id, shippingStatus);
	}
	
	@PostMapping("/{member_id}/unplaced")
	//購物車:未成立狀態的訂單
	public OrderInfoVO addCart(@PathVariable(value = "member_id") int id) {
		return orderInfoService.addCart(id);
	}
	
	@GetMapping("/orderstatus/{order_status}")
	public List<OrderInfoVO> findByOrderStatus() {
		return orderInfoService.findByOrderStatus();
	}
	
	@GetMapping("/paymentstatus/{payment_status}")
	public List<OrderInfoVO> findByPaymentStatus(@PathVariable(value = "payment_status") String status) {
		return orderInfoService.findByPaymentStatus(status);
	}
	
	@GetMapping("/shippingstatus/{shipping_status}")
	public List<OrderInfoVO> findByShippingStatus(@PathVariable(value = "shipping_status") String status) {
		return orderInfoService.findByShippingStatus(status);
	}
	
	@GetMapping("/datePicker")
	public List<OrderInfoVO> datePicker() {
		//TODO
		return null;
		
	}
}
