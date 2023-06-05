package idv.tha101.extractp.store.service.impl;

import idv.tha101.extractp.store.dao.StoreRepository;
import idv.tha101.extractp.store.pojo.Store;
import idv.tha101.extractp.store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository repository;

    @Override
    public List<Store> findAll() {
        return repository.findAll();
    }

    @Override
    public Store findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Store save(Store store) {
        return repository.save(store);
    }

    @Override
    public Store update(int id, Store updateStore) {
        Optional<Store> optionalStore = repository.findById(id);
        if (optionalStore.isPresent()) {
            Store store = optionalStore.get();

            if (updateStore.getStoreTax() != null) {
                store.setStoreTax(updateStore.getStoreTax());
            }

            if (updateStore.getMemberId() != null) {
                store.setMemberId(updateStore.getMemberId());
            }

            if (updateStore.getStoreName() != null) {
                store.setStoreName(updateStore.getStoreName());
            }

            if (updateStore.getStoreInfo() != null) {
                store.setStoreInfo(updateStore.getStoreInfo());
            }

            if (updateStore.getStoreAddress() != null) {
                store.setStoreAddress(updateStore.getStoreAddress());
            }

            if (updateStore.getStoreTime() != null) {
                store.setStoreTime(updateStore.getStoreTime());
            }

            if (updateStore.getStorePhone() != null) {
                store.setStorePhone(updateStore.getStorePhone());
            }

            if (updateStore.getStoreWebsite() != null) {
                store.setStoreWebsite(updateStore.getStoreWebsite());
            }
            return repository.save(store);
        }
        return null;
    }

    @Override
    public List<String> findAllAddresses() {
        List<Store> stores = repository.findAll();
        List<String> addresses = new ArrayList<>();

        for (Store store : stores) {
            addresses.add(store.getStoreAddress());
        }

        return addresses;
    }

    @Override
    public List<Store> findByMemberId(int memberId) {
        return repository.findByMemberId(memberId);
    }
}
