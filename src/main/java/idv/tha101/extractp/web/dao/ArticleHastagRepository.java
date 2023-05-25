package idv.tha101.extractp.web.dao;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import idv.tha101.extractp.base.dao.BaseRepository;
import idv.tha101.extractp.web.pojo.ArticleHastagVO;


@RepositoryRestResource
@Repository
public interface ArticleHastagRepository extends BaseRepository<ArticleHastagVO, Integer>{

}
