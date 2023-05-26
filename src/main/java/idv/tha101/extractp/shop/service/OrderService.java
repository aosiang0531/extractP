package idv.tha101.extractp.shop.service;

import java.util.List;

import idv.tha101.extractp.base.service.BaseService;
import idv.tha101.extractp.shop.pojo.OrderVO;

public interface OrderService extends BaseService<OrderVO>{

	List<OrderVO> findByOrder_status(String status);


}