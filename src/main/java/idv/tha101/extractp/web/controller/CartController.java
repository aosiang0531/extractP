//package idv.tha101.extractp.web.controller;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
//import idv.tha101.extractp.member.pojo.MemberVO;
//import idv.tha101.extractp.web.pojo.OrderDetailVO;
//import idv.tha101.extractp.web.pojo.ProductVO;
//import idv.tha101.extractp.web.service.CartService;
//import idv.tha101.extractp.web.service.OrderDetailService;
//import idv.tha101.extractp.web.service.OrderInfoService;
//import idv.tha101.extractp.web.service.ProductService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//
//@RestController
//@RequestMapping("shop/cart")
//@SessionAttributes({ "member" })
//public class CartController {
//
//	@Autowired
//	private CartService cartService;
//	@Autowired
//	private ProductService productService;
//	@Autowired
//	private OrderInfoService orderService;
//	@Autowired
//	private OrderDetailService orderDetailService;
//
//	// 測試用的假登入會員，記得刪！
//	@GetMapping("/view")
//	public MemberVO viewCart(@ModelAttribute("member") MemberVO member, Model model) {
//		// 在購物車中使用會員資訊
//		// 可以使用 member.getId() 等方法獲取會員相關資訊
////		if (member != null) {
//		return member;
////		} else {
////			return null;
////		}
//	}
//
//	@GetMapping("getList")
////	public List<OrderDetailVO> getCartItem(HttpServletRequest request) {
//	public List<Map<String, Object>> getCartItem(HttpServletRequest request) {
//		// 參數false：只需要查詢session 而不需要創建新的session。
//		HttpSession session = request.getSession(false);
//		if (session != null) {
//
//			// 從session中獲取已登入的會員資訊
//			MemberVO member = (MemberVO) session.getAttribute("member");
//			if (member != null) {
//
//				int memberId = member.getId();
//				System.out.println(memberId + "號會員開始查看購物車");
//
//				List<OrderDetailVO> orderDetails = cartService.getCartItems(memberId);
//				System.out.println("購物中共有" + orderDetails.size() + "商品");//				
//				List<Map<String, Object>> itemList = new ArrayList<>();
//				
//				//獲取購買數量、並以商品id獲取其他資訊，放入集合
//				for(OrderDetailVO item : orderDetails) {
//					Map<String, Object> cartItems = new HashMap<>();
//					int quantity = item.getQuantity();
//					int productId = item.getId();
//					ProductVO product = productService.findById(productId);//					
//					cartItems.put("productImage", product.getImage());
//					cartItems.put("productName", product.getName());
//					cartItems.put("productSpec", product.getSpec());
//					cartItems.put("price", product.getPrice());
//					cartItems.put("quantity", quantity);
//					itemList.add(cartItems);
//				}
////				return orderDetails;
//				return itemList;
//
//			} else {
//				// 無已登入會員的情況
//				System.out.println("無會員登入");
//			}
//
//		} else {
//			// 沒有 HttpSession，處理沒有會話的情況
//			System.out.println("無session");
//		}
//		return null; ////
//	}
//
////	@GetMapping("getList")
////	public List<OrderDetailVO> getCartItem(HttpServletRequest request) {
////		HttpSession session = request.getSession(false);
////		if (session != null) {
////			
////			// 獲取會員資訊
////			MemberVO member = (MemberVO) session.getAttribute("member");
////			if (member != null) {
////
////				int memberId = member.getId();
////				System.out.println(memberId + "號會員查看購物車");
////
////				// 找出會員的所有訂單
////				List<OrderVO> orderList = service.getOrdersByMemberId(memberId);
//////				System.out.println("找到" + orderList.size() + "筆訂單");
////				
////				// 沒任何訂單時，建立購物車
////				if (orderList == null) {
////					OrderVO cart = service.addNewCart(memberId);
////					System.out.println("建立新購物車…");
////					// 回傳空的購物車清單
////					return null;
////				} else {
////					// 有訂單資料，找出其中的購物車
////					OrderVO cart = service.findCart(orderList);
////					if(cart != null) {
////						
////						
////					return orderDetailService.getOrderDetalByOrderId(cart.getId());
////					
////					}else {
////						//沒購物車就新增一個
////						service.addNewCart(memberId);
////						return null;
////					}
////				}
////
////			} else {
////				System.out.println("會員未登入，載入本地端購物車資訊");
////				return null;
////			}
////		} else {
////			// 沒有 HttpSession，處理沒有會話的情況
////			System.out.println("無session");
////		}
////		System.out.println("無購物車清單");
////		return null;
////
////	}
////	@GetMapping("getList")
////	public List<OrderDetailVO> findCart() {
////		// 進入購物車時，判斷是否已存在購物車清單
////		List<OrderVO> cartInOrder = service.findByOrderStatus("未成立");
////		// 其值為空陣列，代表無現有的購物車，則新建一個
////		if (cartInOrder.size() == 0) {
////			System.out.println("無購物車，建立中…");
////			OrderVO order = new OrderVO();
////			order.setStatus("未成立");
////			order.setMember_id(null); //尚需寫入會員編號！！！！！！
////			service.saveOrUpdate(order);
////			// 獲得購物車ID
////			int order_id = order.getId();
////			System.out.println("購物車編號：" + order_id);
////			return OrderDservice.findByOrderId(order_id);
////		} else if (cartInOrder.size() == 1) {
////			//載入現有的購物車
////			OrderVO cart = cartInOrder.get(0);
////			int cartId = cart.getId();
////			List<OrderDetailVO> cartDetail = OrderDservice.findByOrderId(cartId);
////			System.out.println("購物車編號：" + cartId + "，共載入" + cartId + "項清單");
////			return cartDetail;
////		}
////
////		System.out.println("購物車異常，請洽管理員");
////		return null;
////
////	}
//
//}
