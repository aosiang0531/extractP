package idv.tha101.extractp.web.dao;

import java.util.Optional;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import idv.tha101.extractp.base.dao.BaseRepository;
import idv.tha101.extractp.web.pojo.MemberVO;


@RepositoryRestResource
@Repository
public interface MemberRepository extends BaseRepository<MemberVO, Integer> {

	boolean existsByEmail(String email);
	
	MemberVO findByEmailAndPassword(String email, String password);
	
	Optional<MemberVO> findByEmail(String email);

}
