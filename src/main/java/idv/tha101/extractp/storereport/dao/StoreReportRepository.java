package idv.tha101.extractp.storereport.dao;

import idv.tha101.extractp.storereport.pojo.StoreReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface StoreReportRepository extends JpaRepository<StoreReport, Integer> {
}
