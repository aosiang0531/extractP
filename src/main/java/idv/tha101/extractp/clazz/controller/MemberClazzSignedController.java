package idv.tha101.extractp.clazz.controller;

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
import idv.tha101.extractp.clazz.pojo.MemberClazzSignedVO;
import idv.tha101.extractp.clazz.service.MemberClazzSignedService;


@RestController
@RequestMapping("memberClazzSigned")
	public class MemberClazzSignedController  extends BaseController<MemberClazzSignedVO> {

		@Autowired
		private MemberClazzSignedService memberClazzSigned;
		
		@Override
		@GetMapping
		public List<MemberClazzSignedVO> findAll() {

			return memberClazzSigned.findAll();
		}

		@Override
		@GetMapping("/{id}")
		public MemberClazzSignedVO findById(@PathVariable(value = "id") int id) {
			return memberClazzSigned.findById(id);
		}

		@Override
		@PostMapping
		public MemberClazzSignedVO save(@RequestBody MemberClazzSignedVO vo) {
			return memberClazzSigned.saveOrUpdate(vo);
		};

		@Override
		@PutMapping("/{id}")
		public MemberClazzSignedVO update(@RequestBody MemberClazzSignedVO vo, @PathVariable(value = "id") int id) {
			return memberClazzSigned.saveOrUpdate(vo.setId(id));
		}

		@Override
		@DeleteMapping("/{id}")
		public void deleteById(@PathVariable(value = "id") int id) {
			memberClazzSigned.deleteById(id);
		}
	}

