package idv.tha101.extractp.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import idv.tha101.extractp.base.controller.BaseController;
import idv.tha101.extractp.sample.pojo.SampleVO;
import idv.tha101.extractp.sample.service.SampleService;

@RestController
@RequestMapping("sample")
public class SampleController extends BaseController<SampleVO> {

	@Autowired
	private SampleService sampleService;

	@GetMapping
	public List<SampleVO> findAll() {
		return sampleService.findAll();
	}

	@GetMapping("/{id}")
	public SampleVO findById(@PathVariable(value = "id") int id) {
		return sampleService.findById(id);
	}

	@PostMapping
	public SampleVO save(@RequestBody SampleVO vo) {
		return sampleService.save(vo);
	};
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable(value = "id") int id) {
		sampleService.deleteById(id);
	}

}
