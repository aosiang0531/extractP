package idv.tha101.extractp.store.controller;

import idv.tha101.extractp.store.pojo.Store;
import idv.tha101.extractp.store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stores")
public class StoreController {

    @Autowired
    private StoreService service;

    @GetMapping
    public List<Store> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Store findById(@PathVariable(value = "id") int id) {
        return service.findById(id);
    }

    @PostMapping
    public Store save(@RequestBody Store store) {
        return service.save(store);
    }

    @PutMapping("/{id}")
    public Store update(@RequestBody Store store, @PathVariable(value = "id") int id) {
        return service.update(id, store);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(value = "id") int id) {
        service.deleteById(id);
    }

    @GetMapping("/addresses")
    public List<String> getStoreAddresses() {
        return service.findAllAddresses();
    }


}
