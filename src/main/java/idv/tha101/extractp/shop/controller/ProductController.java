package idv.tha101.extractp.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import idv.tha101.extractp.base.controller.BaseController;
import idv.tha101.extractp.shop.pojo.ProductVO;
import idv.tha101.extractp.shop.service.ProductService;

@RestController
@RequestMapping("shop/product")
public class ProductController extends BaseController<ProductVO> {

	@Autowired
	private ProductService service;

	@Override
	@GetMapping
	public List<ProductVO> findAll() {
		System.out.println("載入所有商品");
		return service.findAll();
	}

	@Override
	@GetMapping("/{id}")
	public ProductVO findById(@PathVariable(value = "id") int id) {
		return service.findById(id);
	}

	@Override
	@PostMapping
	public ProductVO save(@RequestBody ProductVO product) {
		return service.saveOrUpdate(product);
	}

	@Override
	@PutMapping("/{id}")
	public ProductVO update(@RequestBody ProductVO vo, @PathVariable(value = "id") int id) {
		return service.saveOrUpdate(vo.setId(id));
	}

	@Override
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value = "id") int id) {
		service.deleteById(id);
	}
	
//  @GetMapping("/product-single.html")
//	public ProductVO singleProduct( @RequestParam("id") int id, Model model) {
//		 ProductVO product = service.findById(id);
//		 model.addAttribute("product", product);
//		 
//		 return product;
//	}
	
//	@Override
//	@GetMapping("/SingleProduct")	//http://localhost:8080/product/SingleProduct?id=
//	public ProductVO findById(@RequestParam("id") int id) {
//		return service.findById(id);
//	}
	


}
;