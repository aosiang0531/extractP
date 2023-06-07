package idv.tha101.extractp.store.dao;

import idv.tha101.extractp.store.pojo.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
    List<Store> findByMemberId(int memberId);
}


