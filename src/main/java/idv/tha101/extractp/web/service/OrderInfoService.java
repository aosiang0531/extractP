package idv.tha101.extractp.web.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import idv.tha101.extractp.base.service.BaseService;
import idv.tha101.extractp.web.pojo.OrderInfoVO;

public interface OrderInfoService extends BaseService<OrderInfoVO> {

	List<OrderInfoVO> findByMemberIdAndOrderStatus(Integer member_id, String status);

	List<OrderInfoVO> findByMemberId(Integer member_id);

	List<OrderInfoVO> findByMemberIdAndPaymentStatus(Integer member_id, String status);

	List<OrderInfoVO> findByMemberIdAndShippingStatus(Integer member_id, String status);

	List<OrderInfoVO> findByOrderStatus();

	List<OrderInfoVO> findByPaymentStatus(String status);

	List<OrderInfoVO> findByShippingStatus(String status);

	OrderInfoVO addCart(Integer member_id);

	List<OrderInfoVO> pickDate(Integer member_id, Timestamp date1, Timestamp date2);


}