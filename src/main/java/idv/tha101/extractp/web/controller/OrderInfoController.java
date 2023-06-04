package idv.tha101.extractp.web.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import idv.tha101.extractp.web.pojo.OrderInfoVO;
import idv.tha101.extractp.web.service.OrderInfoService;
import jakarta.transaction.Transactional;

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
	
	@GetMapping("/{member_id}/orderStatus/{order_status}")
	public List<OrderInfoVO> findByMemberOrderStatus(
			@PathVariable(value = "member_id") int id, 
			@PathVariable(value = "order_status") String orderstatus) {
		return orderInfoService.findByMemberIdAndOrderStatus(id, orderstatus);
	}

	@GetMapping("/{member_id}/paymentStatus/{payment_status}")
	public List<OrderInfoVO> findByMemberPaymentStatus(
			@PathVariable(value = "member_id") int id, 
			@PathVariable(value = "payment_status") String paymentStatus) {
		return orderInfoService.findByMemberIdAndPaymentStatus(id, paymentStatus);
	}
	
	@GetMapping("/{member_id}/shippingStatus/{shipping_status}")
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
	
	@GetMapping("/orderStatus")
	public List<OrderInfoVO> findByOrderStatus() {
		return orderInfoService.findByOrderStatus();
	}
	
	@GetMapping("/paymentStatus/{payment_status}")
	public List<OrderInfoVO> findByPaymentStatus(@PathVariable(value = "payment_status") String status) {
		return orderInfoService.findByPaymentStatus(status);
	}
	
	@GetMapping("/shippingStatus/{shipping_status}")
	public List<OrderInfoVO> findByShippingStatus(@PathVariable(value = "shipping_status") String status) {
		return orderInfoService.findByShippingStatus(status);
	}
	
	@PostMapping("/pickDate")
	public List<OrderInfoVO> pickDate(@RequestBody Map<String, String> map) {
		String date1 = map.get("date1");
		String date2 = map.get("date2");
		Integer member_id = Integer.parseInt(map.get("member_id"));
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateTime1 = LocalDate.parse(date1, formatter);
		Timestamp timestamp1 = Timestamp.valueOf(dateTime1.atStartOfDay());
		
		LocalDate dateTime2 = LocalDate.parse(date2, formatter);
		Timestamp timestamp2 = Timestamp.valueOf(dateTime2.atStartOfDay());
		
		return orderInfoService.pickDate(member_id, timestamp1, timestamp2);
	}
	
//	@PutMapping("/updateShippingStatus")
//	@Transactional
//	public Map<String, Integer> updateShippingStatus(@RequestBody Map<String, String> map) {
//		//TODO
//		Map<String, Integer> result = new HashMap<>();
//		int id = Integer.parseInt(map.get("order_id"));
//		String shippingStatus = map.get("order_shipping_status");
//		if("待出貨".equals(shippingStatus)) {
//			OrderInfoVO orderInfoVO = orderInfoService.saveOrUpdate(null)
//		}
//		
//		return null;
//		
//	}
}
