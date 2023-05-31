package idv.tha101.extractp.ad.controller;

import idv.tha101.extractp.ad.pojo.Ad;
import idv.tha101.extractp.ad.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("ad")
@RestController
public class AdController {

    @Autowired
    private AdService service;

    @GetMapping
    public List<Ad> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Ad findById(@PathVariable(value = "id") int id) {
        return service.findById(id);
    }

    @PostMapping
    public Ad save(@RequestBody Ad ad) {
        return service.save(ad);
    }

    @PutMapping("/{id}")
    public Ad update(@RequestBody Ad ad, @PathVariable(value = "id") int id) {
        return service.save(ad);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(value = "id") int id) {
        service.deleteById(id);
    }

}
