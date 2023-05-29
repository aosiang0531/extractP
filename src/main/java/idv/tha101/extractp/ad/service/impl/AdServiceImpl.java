package idv.tha101.extractp.ad.service.impl;


import idv.tha101.extractp.ad.dao.AdRepository;
import idv.tha101.extractp.ad.pojo.Ad;
import idv.tha101.extractp.ad.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdRepository repository;

    @Override
    public List<Ad> findAll() {
        return repository.findAll();
    }

    @Override
    public Ad findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Ad save(Ad ad) {
        return repository.save(ad);
    }
}
