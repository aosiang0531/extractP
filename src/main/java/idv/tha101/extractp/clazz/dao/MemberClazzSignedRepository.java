package idv.tha101.extractp.clazz.dao;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import idv.tha101.extractp.base.dao.BaseRepository;
import idv.tha101.extractp.clazz.pojo.MemberClazzSignedVO;

@RepositoryRestResource
@Repository
public interface MemberClazzSignedRepository extends BaseRepository<MemberClazzSignedVO, Integer> {

}
