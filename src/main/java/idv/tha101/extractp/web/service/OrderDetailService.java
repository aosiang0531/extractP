package idv.tha101.extractp.web.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import idv.tha101.extractp.base.service.BaseService;
import idv.tha101.extractp.web.pojo.OrderDTO;
import idv.tha101.extractp.web.pojo.OrderDetailVO;

public interface OrderDetailService extends BaseService<OrderDetailVO> {

	List<OrderDetailVO> findByOrderId(Integer order_id);

	HashMap<String, Double> countTotal(Integer order_id);

	Collection<OrderDTO> findOrderInfo(Integer id);

}
