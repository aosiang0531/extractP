package idv.tha101.extractp.ad.dao;

import idv.tha101.extractp.ad.pojo.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
@Repository
public interface AdRepository extends JpaRepository <Ad, Integer>{
}
