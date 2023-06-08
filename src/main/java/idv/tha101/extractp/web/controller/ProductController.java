package idv.tha101.extractp.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import idv.tha101.extractp.base.controller.BaseController;
import idv.tha101.extractp.web.pojo.ProductVO;
import idv.tha101.extractp.web.service.ProductService;

@RestController
@RequestMapping("shop/product")
public class ProductController extends BaseController<ProductVO> {

	@Autowired
	private ProductService service;

	// 以關鍵字+產品分類查詢商品
	@GetMapping("/search/{keyword}/{categoryId}")
	public List<ProductVO> findByKeywordAndCategory(@PathVariable(value = "keyword") String keyword,
			@PathVariable(value = "categoryId") int categoryId) {
		System.out.println("start searching..");
		return service.findByProductNameAndCategoryId("%" + keyword + "%", categoryId);
	}

	// 以關鍵字模糊查詢「上架中」商品
	@GetMapping("/search/{keyword}")
	public List<ProductVO> findAvailableByKeyword(@PathVariable(value = "keyword") String keyword) {
		System.out.println("start searching..");
		return service.findByNameAndStatus("%" + keyword + "%", "上架中");
	}

	// 以關鍵字模糊查詢所有商品
	@GetMapping("/searchAll/{keyword}")
	public List<ProductVO> findByKeyword(@PathVariable(value = "keyword") String keyword) {
		System.out.println("start searching..");
		return service.findByProductNameLike("%" + keyword + "%");
	}

	// 查詢所有「已上架」商品
	@GetMapping("/onSale")
	public List<ProductVO> findAllByStatus() {
		System.out.println("載入所有已上架商品");
		return service.findByStatus("上架中");
	}

	// 查詢所有「已上架」商品(分頁版本)
	@GetMapping("/onSalePaged")
	public Page<ProductVO> findAllOnSale(Pageable pageable) {
		return service.findAllOnSale(pageable);
	}

	@GetMapping("/onSale/sort/{method}")
	public List<ProductVO> findAllByStatusSort(@PathVariable(value = "method") String method) {
		System.out.println("將上架商品排序中…");
		List<ProductVO> list = service.findByStatus("上架中");
		if ("asc".equals(method)) {
			// 由低至高排列
			return list.stream().sorted(Comparator.comparing(ProductVO::getPrice)).collect(Collectors.toList());
		} else if ("desc".equals(method)) {
			// 由高至低排列
			return list.stream().sorted(Comparator.comparing(ProductVO::getPrice).reversed())
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
		} else if ("unAvailable".equals(status)) {
			System.out.println("正在上架…");
			product.setProductStatus("上架中");
			service.saveOrUpdate(product);
			System.out.println("上架成功！");
		}
	}

	// 查詢已售完商品
	@GetMapping("/soldOut")
	public List<ProductVO> findAllBySoldOut() {
		System.out.println("載入所有售完商品");
		return service.findByProductStockZero();
	}

	@GetMapping("/all")
	public Page<ProductVO> findAllPaged(Pageable pageable) {
		System.out.println("載入所有商品");
		return service.findAll(pageable);
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

//	@GetMapping("/SingleProduct") //從QueryString獲取參數的方式，例如：/SingleProduct?id=
//	public ProductVO findById(@RequestParam("id") int id) {
//		return service.findById(id);
//	}

	@PostMapping("testUpload")
	public Map<String, Object> createProduct(@RequestParam("category") int category,
			@RequestParam("productName") String productName,
			@RequestParam(value = "images", required = false) List<MultipartFile> images) {

		System.out.println(category);
		System.out.println(productName);
		int imageCount;

		// 檢查 images 是否為空
		if (images.get(0) == null || images.get(0).isEmpty()) {
			imageCount = 0;
		} else {
			int serialNumber = 1;
			imageCount = images.size();
			System.out.println(imageCount);
//	    	
			ProductVO p = new ProductVO();
			// 遍歷處理每個圖片
			List<String> imagePaths = new ArrayList<>();
			for (MultipartFile image : images) {
				p.setStock(1);
				p.setProductName(productName);
				p.setPrice(100);
				int productId = service.saveOrUpdate(p).getId();
				// 使用String.format()方法組合檔名，03d為補0到三位數
				String fileName = String.format("%d_%d_%03d.jpg", category, productId, serialNumber);
				serialNumber++;
				// 將圖片數據存儲到目標文件夾，使用組合後的檔名
				String targetFilePath = "/path/folder/" + fileName;

				try {
					// 將圖片數據存儲到目標文件夾
					image.transferTo(new File(targetFilePath));

					// 將存儲後的文件路徑添加到集合中
					imagePaths.add(targetFilePath);

//	                
				} catch (IOException e) {
					// 處理存儲失敗的異常情況
					e.printStackTrace();
				}
			}
		}
		//用Map可以自行組合出要回傳給前端的json格式key,value
		Map<String, Object> map = new HashMap<>();
		map.put("分類", category);
		map.put("品名", productName);
		map.put("圖數", imageCount);
		map.put("成功", "ture");
		return map;
	}

};