package idv.tha101.extractp.web.dao;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import idv.tha101.extractp.base.dao.BaseRepository;
import idv.tha101.extractp.web.pojo.AdminVO;

@RepositoryRestResource
@Repository
public interface AdminRepostiory extends BaseRepository<AdminVO, Integer>{

	boolean existsByEmail(String email);

}
