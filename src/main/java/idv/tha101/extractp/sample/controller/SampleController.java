package idv.tha101.extractp.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import idv.tha101.extractp.sample.service.SampleService;

@RestController
@RequestMapping("sample")
public class SampleController {
	
	@Autowired
	private SampleService sampleService;

}
