package idv.tha101.extractp.clazz.dao;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import idv.tha101.extractp.base.dao.BaseRepository;
import idv.tha101.extractp.clazz.pojo.ClazzVO;

@RepositoryRestResource
@Repository
//角括號裡的左邊是VO，右邊則是該VO裡面table的主鍵
public interface ClazzRepository extends BaseRepository<ClazzVO, Integer> {
	
	//用課程狀態查詢
//	List<ClazzVO> findByClazzStatus(@Param("class_status") String class_status);
	
}
