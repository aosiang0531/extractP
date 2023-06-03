package idv.tha101.extractp.store.service;

import idv.tha101.extractp.store.pojo.Store;

import java.util.List;

public interface StoreService {
    List<Store> findAll();

    Store findById(Integer id);

    void deleteById(Integer id);

    Store save(Store store);

    Store update(int id, Store updateStore);

    List<String> findAllAddresses();
}
