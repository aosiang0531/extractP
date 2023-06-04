package idv.tha101.extractp.web.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
import idv.tha101.extractp.web.pojo.ProductVO;
import idv.tha101.extractp.web.service.ProductService;

@RestController
@RequestMapping("shop/product")
public class ProductController extends BaseController<ProductVO> {

	@Autowired
	private ProductService service;
	
	
	//以關鍵字+產品分類查詢商品
		@GetMapping("/search/{keyword}/{categoryId}")
		public List<ProductVO> findByKeywordAndCategory(@PathVariable(value = "keyword") String keyword,@PathVariable(value = "categoryId") int categoryId){
			System.out.println("start searching..");
			return service.findByProductNameAndCategoryId("%" + keyword + "%",categoryId);
		}
	
	//以關鍵字模糊查詢商品
	@GetMapping("/search/{keyword}")
	public List<ProductVO> findByKeyword(@PathVariable(value = "keyword") String keyword){
		System.out.println("start searching..");
		return service.findByProductNameLike("%" + keyword + "%");
	}
	
	// 查詢所有「已上架」商品
	@GetMapping("/onSale")
	public List<ProductVO> findAllByStatus() {
		System.out.println("載入所有已上架商品");

		return service.findByStatus("上架中");
	}
	
	@GetMapping("/onSale/sort/{method}")
	public List<ProductVO> findAllByStatusSort(@PathVariable(value = "method") String method) {
		System.out.println("將上架商品排序中…");
		List<ProductVO> list = service.findByStatus("上架中");
		if("asc".equals(method)) {
			//由低至高排列
			return list.stream()
					.sorted(Comparator.comparing(ProductVO::getPrice))
					.collect(Collectors.toList());
		}else if("desc".equals(method)) {
			//由高至低排列
			return list.stream()
					.sorted(Comparator.comparing(ProductVO::getPrice).reversed())
					.collect(Collectors.toList());
		}
		return list;
	}

	// 改變商品上架狀態
	@PostMapping("/{id}/status/{status}")
	public void changeProductStatus(@PathVariable(value = "id") int id, @PathVariable(value = "status") String status) {
		ProductVO product = service.findById(id);
		System.out.println(product);
		System.out.println(status);
		if ("available".equals(status)) {
			System.out.println("正在下架…");
			product.setProductStatus("已下架");
			service.saveOrUpdate(product);
			System.out.println("下架成功！");
		}else if ("unAvailable".equals(status)) {
			System.out.println("正在上架…");
			product.setProductStatus("上架中");
			service.saveOrUpdate(product);
			System.out.println("上架成功！");
		}
	}

	// 查詢已售完商品
	@GetMapping("/soldOut")
	public List<ProductVO> findAllBySoldOut() {
//		System.out.println("載入所有售完商品");
		return service.findByProductSoldCountZero();
	}

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
		System.out.println("新增商品中…");
		product.setSuccessful(true);
		return service.saveOrUpdate(product);
	}

	@Override
	@PutMapping("/{id}")
	public ProductVO update(@RequestBody ProductVO vo, @PathVariable(value = "id") int id) {
		vo.setSuccessful(true);
		return service.saveOrUpdate(vo.setId(id));
	}

	@Override
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value = "id") int id) {
		service.deleteById(id);
		System.out.println("已刪除" + id + "號產品");
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

};