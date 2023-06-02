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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import idv.tha101.extractp.base.controller.BaseController;
import idv.tha101.extractp.web.pojo.ClazzVO;
import idv.tha101.extractp.web.service.ClazzService;


@RestController
@RequestMapping("clazz")
	public class ClazzController  extends BaseController<ClazzVO> {

		@Autowired
		private ClazzService clazzService ;
		
		@Override
		@GetMapping
		public List<ClazzVO> findAll() {

			return clazzService.findAll();
		}
		
		@GetMapping("/init")
		public String init() {
			// 调用 findAll() 获取数据
			List<ClazzVO> clazzList = findAll();

			// 创建一个包含 "data" 属性的 JsonObject
			JsonObject jsonObject = new JsonObject();
			JsonArray jsonArray = new Gson().toJsonTree(clazzList).getAsJsonArray();
			jsonObject.add("data", jsonArray);

			// 将 JsonObject 转换为 JSON 字符串
			String json = jsonObject.toString();
			return json;

		}

		@Override
		@GetMapping("/{id}")
		public ClazzVO findById(@PathVariable(value = "id") int id) {
			return clazzService.findById(id);
		}

		@Override
		@PostMapping
		public ClazzVO save(@RequestBody ClazzVO vo) {
			return clazzService.saveOrUpdate(vo);
		};

		@Override
		@PutMapping("/{id}")
		public ClazzVO update(@RequestBody ClazzVO vo, @PathVariable(value = "id") int id) {
			return clazzService.saveOrUpdate(vo.setId(id));
		}

		@Override
		@DeleteMapping("/{id}")
		public void deleteById(@PathVariable(value = "id") int id) {
			clazzService.deleteById(id);
		}
	}

