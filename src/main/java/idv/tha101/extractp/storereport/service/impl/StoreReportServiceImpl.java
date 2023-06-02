package idv.tha101.extractp.storereport.service.impl;

import idv.tha101.extractp.storereport.dao.StoreReportRepository;
import idv.tha101.extractp.storereport.pojo.StoreReport;
import idv.tha101.extractp.storereport.service.StoreReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreReportServiceImpl implements StoreReportService {

    @Autowired
    private StoreReportRepository repository;
    @Override
    public List findAll() {
        return repository.findAll();
    }

    @Override
    public StoreReport findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public StoreReport save(StoreReport storeReport) {
        return repository.save(storeReport);
    }
}
