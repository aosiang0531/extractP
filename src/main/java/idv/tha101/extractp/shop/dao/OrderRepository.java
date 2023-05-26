package idv.tha101.extractp.shop.dao;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import idv.tha101.extractp.base.dao.BaseRepository;
import idv.tha101.extractp.shop.pojo.OrderVO;

@RepositoryRestResource
@Repository
public interface OrderRepository extends BaseRepository<OrderVO, Integer>{
	
	List<OrderVO> findByOrderStatus(@Param("status") String status);

}