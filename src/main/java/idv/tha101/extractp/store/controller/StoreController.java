package idv.tha101.extractp.store.controller;

import idv.tha101.extractp.store.pojo.Store;
import idv.tha101.extractp.store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @GetMapping("member/{memberId}")
    public List<Store> findByMemberId(@PathVariable(value = "memberId") int memberId) {
        return service.findByMemberId(memberId);
    }

    @GetMapping("/{id}")
    public Store findById(@PathVariable(value = "id") int id) {
        return service.findById(id);
    }

    @PostMapping
    public Store save(@RequestBody Store store) throws IOException {
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

}
