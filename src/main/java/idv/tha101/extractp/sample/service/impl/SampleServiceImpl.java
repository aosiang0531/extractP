package idv.tha101.extractp.sample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.sample.dao.SampleRepositry;
import idv.tha101.extractp.sample.service.SampleService;

@Service
public class SampleServiceImpl implements SampleService{
	
	@Autowired
	private SampleRepositry sampleRepositry;
	
}
