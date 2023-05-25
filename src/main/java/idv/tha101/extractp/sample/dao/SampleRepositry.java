package idv.tha101.extractp.sample.dao;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import idv.tha101.extractp.base.dao.BaseRepositry;
import idv.tha101.extractp.sample.pojo.SampleVO;

@RepositoryRestResource
@Repository
public interface SampleRepositry extends BaseRepositry<SampleVO, Integer> {

}
