package idv.tha101.extractp.shop.service;

import java.util.List;

import idv.tha101.extractp.shop.pojo.OrderDetailVO;
import idv.tha101.extractp.shop.pojo.OrderVO;

public interface CartService {

	// 以會員id找出購物車內所有明細
	List<OrderDetailVO> getCartItems(int memberId);

	// 以會員id找出購物車("未成立")，沒有就建立
	OrderVO getCartByMemberId(int memberId);

	// 建立新購物車
	OrderVO addNewCart(int memberId);

}