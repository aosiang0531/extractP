package idv.tha101.extractp.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepositry<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

}
