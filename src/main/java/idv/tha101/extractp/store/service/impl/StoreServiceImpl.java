package idv.tha101.extractp.store.service.impl;

import idv.tha101.extractp.store.dao.StoreRepository;
import idv.tha101.extractp.store.pojo.Store;
import idv.tha101.extractp.store.service.StoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

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
}
