package idv.tha101.extractp.web.dao;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import idv.tha101.extractp.base.dao.BaseRepository;
import idv.tha101.extractp.web.pojo.EventVO;


@RepositoryRestResource
@Repository
//角括號裡的左邊是VO，右邊則是該VO裡面table的主鍵
public interface EventRepository extends BaseRepository<EventVO, Integer> {
	
	
}
