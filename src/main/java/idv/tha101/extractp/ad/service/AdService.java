package idv.tha101.extractp.ad.service;

import idv.tha101.extractp.ad.pojo.Ad;
import idv.tha101.extractp.store.pojo.Store;

import java.util.List;

public interface AdService <T>{

    List<T> findAll();

    Ad findById(Integer id);

    void deleteById(Integer id);

    Ad save(Ad ad);
}
