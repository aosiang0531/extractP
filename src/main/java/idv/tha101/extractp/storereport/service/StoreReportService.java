package idv.tha101.extractp.storereport.service;

import idv.tha101.extractp.store.pojo.Store;
import idv.tha101.extractp.storereport.pojo.StoreReport;

import java.util.List;

public interface StoreReportService<T> {
    List<T> findAll();

    StoreReport findById(Integer id);

    void deleteById(Integer id);

    StoreReport save(StoreReport storeReport);
}
