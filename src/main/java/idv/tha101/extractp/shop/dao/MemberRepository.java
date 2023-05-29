package idv.tha101.extractp.shop.dao;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import idv.tha101.extractp.base.dao.BaseRepository;
import idv.tha101.extractp.shop.pojo.MemberVO;

@RepositoryRestResource
@Repository
public interface MemberRepository extends BaseRepository<MemberVO, Integer>{

}
