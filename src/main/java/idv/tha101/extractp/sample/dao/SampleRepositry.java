package idv.tha101.extractp.sample.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import idv.tha101.extractp.sample.pojo.SamplePojo;

@RepositoryRestResource
public interface SampleRepositry extends CrudRepository<SamplePojo, Integer>{

}
