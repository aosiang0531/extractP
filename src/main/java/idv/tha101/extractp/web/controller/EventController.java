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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import idv.tha101.extractp.base.controller.BaseController;
import idv.tha101.extractp.web.pojo.ClazzVO;
import idv.tha101.extractp.web.pojo.EventVO;
import idv.tha101.extractp.web.service.EventService;


@RestController
@RequestMapping("event")
	public class EventController  extends BaseController<EventVO> {

		@Autowired
		private EventService eventService ;
		
		@Override
		@GetMapping
		public List<EventVO> findAll() {

			return eventService.findAll();
		}
		
		@GetMapping("/init")
		public String init() {
			// 调用 findAll() 获取数据
			List<EventVO> eventList = findAll();

			// 创建一个包含 "data" 属性的 JsonObject
			JsonObject jsonObject = new JsonObject();
			JsonArray jsonArray = new Gson().toJsonTree(eventList).getAsJsonArray();
			jsonObject.add("data", jsonArray);

			// 将 JsonObject 转换为 JSON 字符串
			String json = jsonObject.toString();
			return json;
		}

		@Override
		@GetMapping("/{id}")
		public EventVO findById(@PathVariable(value = "id") int id) {
			return eventService.findById(id);
		}

		@Override
		@PostMapping
		public EventVO save(@RequestBody EventVO vo) {
			return eventService.saveOrUpdate(vo);
		};

		@Override
		@PutMapping("/{id}")
		public EventVO update(@RequestBody EventVO vo, @PathVariable(value = "id") int id) {
			return eventService.saveOrUpdate(vo.setId(id));
		}

		@Override
		@DeleteMapping("/{id}")
		public void deleteById(@PathVariable(value = "id") int id) {
			eventService.deleteById(id);
		}
	}

