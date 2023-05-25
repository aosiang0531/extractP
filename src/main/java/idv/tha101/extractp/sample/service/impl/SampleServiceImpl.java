package idv.tha101.extractp.sample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.sample.dao.SampleRepository;
import idv.tha101.extractp.sample.pojo.SampleVO;
import idv.tha101.extractp.sample.service.SampleService;

@Service
public class SampleServiceImpl implements SampleService{
	
	@Autowired
	private SampleRepository sampleRepositry;

	@Override
	public List<SampleVO> findAll() {
		return sampleRepositry.findAll();		
	}

	@Override
	public SampleVO findById(Integer id) {
		return sampleRepositry.findById(id).orElseThrow();
	}

	@Override
	public SampleVO save(SampleVO vo) {
		return sampleRepositry.save(vo);
	}

	@Override
	public void deleteById(Integer id) {
		sampleRepositry.deleteById(id);
	}
	

}
