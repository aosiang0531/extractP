package idv.tha101.extractp.store.service;

import idv.tha101.extractp.store.pojo.Store;

import java.util.List;

public interface StoreService<T> {
    List<T> findAll();

    Store findById(Integer id);

    void deleteById(Integer id);

    Store save(Store store);
}
