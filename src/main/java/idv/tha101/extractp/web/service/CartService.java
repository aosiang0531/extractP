package idv.tha101.extractp.web.service;

import java.util.List;

import idv.tha101.extractp.web.pojo.OrderDetailVO;
import idv.tha101.extractp.web.pojo.OrderInfoVO;

public interface CartService {

	// 以會員id找出購物車內所有明細
	List<OrderDetailVO> getCartItems(int memberId);

	// 以會員id找出購物車("未成立")，沒有就建立
	OrderInfoVO getCartByMemberId(int memberId);

	// 建立新購物車
	OrderInfoVO addNewCart(int memberId);

}