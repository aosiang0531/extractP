package idv.tha101.extractp.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import idv.tha101.extractp.base.controller.BaseController;
import idv.tha101.extractp.web.pojo.ProductCategoryVO;
import idv.tha101.extractp.web.service.ProductCategoryService;

@RestController
@RequestMapping("shop/productCategory")
public class ProductCategoryController extends BaseController<ProductCategoryVO> {
	
	@Autowired
	private ProductCategoryService service;
	
	@Override
	@GetMapping
	public List<ProductCategoryVO> findAll() {
		return	service.findAll();
	}

	@Override
	@GetMapping("/{id}")
	public ProductCategoryVO findById(@PathVariable(value = "id") int id) {
		return service.findById(id);
	}

	@Override
	@PostMapping
	public ProductCategoryVO save(@RequestBody ProductCategoryVO product) {

	return service.saveOrUpdate(product);
	}
	
	@Override
	@PutMapping("/{id}")
	public ProductCategoryVO update(@RequestBody ProductCategoryVO vo, @PathVariable(value = "id") int id) {
		return service.saveOrUpdate(vo.setId(id));
	}
	
	@Override
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value = "id")int id) {
		service.deleteById(id);
	}


}
