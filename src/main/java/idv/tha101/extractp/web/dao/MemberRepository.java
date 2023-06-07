package idv.tha101.extractp.web.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import idv.tha101.extractp.base.dao.BaseRepository;
import idv.tha101.extractp.web.pojo.MemberVO;
import java.util.List;


@RepositoryRestResource
@Repository
public interface MemberRepository extends BaseRepository<MemberVO, Integer> {

	boolean existsByEmail(String email);
	
	MemberVO findByEmailAndPassword(String email, String password);
	
	MemberVO findByEmail(String email);

}
