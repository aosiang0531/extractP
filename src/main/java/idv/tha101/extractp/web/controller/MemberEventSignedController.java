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
import idv.tha101.extractp.web.pojo.MemberEventSignedVO;
import idv.tha101.extractp.web.service.MemberEventSignedService;


@RestController
@RequestMapping("memberEventSigned")
	public class MemberEventSignedController  extends BaseController<MemberEventSignedVO> {

		@Autowired
		private MemberEventSignedService memberEventSigned;
		
		@Override
		@GetMapping
		public List<MemberEventSignedVO> findAll() {

			return memberEventSigned.findAll();
		}

		@Override
		@GetMapping("/{id}")
		public MemberEventSignedVO findById(@PathVariable(value = "id") int id) {
			return memberEventSigned.findById(id);
		}

		@Override
		@PostMapping
		public MemberEventSignedVO save(@RequestBody MemberEventSignedVO vo) {
			return memberEventSigned.saveOrUpdate(vo);
		};

		@Override
		@PutMapping("/{id}")
		public MemberEventSignedVO update(@RequestBody MemberEventSignedVO vo, @PathVariable(value = "id") int id) {
			return memberEventSigned.saveOrUpdate(vo.setId(id));
		}

		@Override
		@DeleteMapping("/{id}")
		public void deleteById(@PathVariable(value = "id") int id) {
			memberEventSigned.deleteById(id);
		}
	}

