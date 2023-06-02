package idv.tha101.extractp.storereport.controller;


import idv.tha101.extractp.ad.pojo.Ad;
import idv.tha101.extractp.storereport.pojo.StoreReport;
import idv.tha101.extractp.storereport.service.StoreReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("storereport")
public class StoreReportController {

    @Autowired
    private StoreReportService service;

    @GetMapping
    public List<Ad> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public StoreReport findById(@PathVariable(value = "id") int id) {
        return service.findById(id);
    }

    @PostMapping
    public StoreReport save(@RequestBody StoreReport storeReport) {
        return service.save(storeReport);
    }

    @PutMapping("/{id}")
    public StoreReport update(@RequestBody StoreReport storeReport, @PathVariable(value = "id") int id) {
        return service.save(storeReport);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(value = "id") int id) {
        service.deleteById(id);
    }
}
