package idv.tha101.extractp.sample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import idv.tha101.extractp.sample.pojo.SamplePojo;

@RepositoryRestResource
public interface SampleRepositry extends JpaRepository<SamplePojo, Integer>{

}
