package idv.tha101.extractp.shop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.shop.dao.OrderDetailRepository;
import idv.tha101.extractp.shop.dao.OrderRepository;
import idv.tha101.extractp.shop.dao.ProductRepository;
import idv.tha101.extractp.shop.pojo.OrderDetailVO;
import idv.tha101.extractp.shop.pojo.OrderVO;
import idv.tha101.extractp.shop.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private OrderDetailRepository detailRepo;
	
	// 以會員id找出購物車內所有明細
	@Override
	public List<OrderDetailVO> getCartItems(int memberId) {
		OrderVO cart = getCartByMemberId(memberId);
		List<OrderDetailVO> itemList = detailRepo.findOrderDetailVOsByOrderId(cart.getId());
		return itemList;
	}
	
	// 以會員id找出購物車("未成立")，沒有就建立
	@Override
	public OrderVO getCartByMemberId(int memberId) {
		List<OrderVO> list = orderRepo.findOrderVOsByMemberId(memberId);
		Optional<OrderVO> result = list.stream().filter(order -> order.getStatus().equals("未成立")).findFirst();
		if (result.isPresent()) {
			OrderVO existCart = result.get();
			System.out.println("找到購物車編號為：" + existCart.getId());
			return existCart;
		} else {
			OrderVO newCart = addNewCart(memberId);
			System.out.println("無現有購物車，新建編號為：" + newCart.getId());
			return newCart;
		}
	}

	// 建立新購物車
	@Override
	public OrderVO addNewCart(int memberId) {
		OrderVO newCart = new OrderVO();
		newCart.setMember_id(memberId);
		newCart.setStatus("未成立");
		orderRepo.save(newCart);
		return newCart;
	}



//	public void addItemToCart(int memberId, int productId, int quantity) {
//		
//	    // 獲取用戶的購物車項目列表
//	    List<OrderDetailVO> cartItems = detailRepo.byidfindByMemberId(memberId);
//	    
//	    // 檢查購物車中是否已經存在該商品
//	    Optional<OrderDetailVO> existingItemOptional = cartItems.stream()
//	            .filter(item -> item.getId() == productId)
//	            .findFirst();
//
//	    if (existingItemOptional.isPresent()) {
//	        // 商品已存在於購物車中，更新數量
//	        OrderDetailVO existingItem = existingItemOptional.get();
//	        existingItem.setQuantity(existingItem.getQuantity() + quantity);
//	        detailRepo.save(existingItem);
//	    } else {
//	        // 商品不存在於購物車中，創建新的購物車項目
//	        OrderDetailVO newItem = new OrderDetailVO();
//	        newItem.setId(memberId);
//	        newItem.setProduct_id(productId);
//	        newItem.setQuantity(quantity);
//	        detailRepo.save(newItem);
//	    }
//    }

//    public void removeItemFromCart(int userId, int productId) {
//        // 從購物車中移除指定產品
//        // 刪除相應的購物車項目
//    }

//    public void updateCartItemQuantity(int userId, int productId, int quantity) {
//        // 更新購物車項目的數量
//        // 更新相應的購物車項目的數量屬性
//    }

//    public void clearCart(int userId) {
//        // 清空購物車
//        // 刪除用戶的所有購物車項目
//    }

//    public double calculateCartTotal(int userId) {
//        // 計算購物車的總價
//        // 根據用戶 ID 獲取購物車項目，然後計算總價
//    }

//    public void checkout(int userId) {
//        // 購物車結帳操作
//        // 創建訂單，更新庫存，處理支付等相關業務邏輯
//    }

}
