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
import idv.tha101.extractp.web.pojo.AdminVO;
import idv.tha101.extractp.web.service.AdminService;

@RestController
@RequestMapping("admin")
public class AdminController extends BaseController<AdminVO> {

	@Autowired
	private AdminService adminService;

	@Override
	@GetMapping
	public List<AdminVO> findAll() {
		return adminService.findAll();
	}

	@Override
	@GetMapping("/{id}")
	public AdminVO findById(@PathVariable(value = "id") int id) {
		return adminService.findById(id);
	}

	@Override
	@PostMapping
	public AdminVO save(AdminVO vo) {
		return adminService.saveOrUpdate(vo);
	}

	@Override
	@PutMapping("/{id}")
	public AdminVO update(@RequestBody AdminVO vo, @PathVariable(value = "id") int id) {
		return adminService.saveOrUpdate(vo.setId(id));
	}

	@Override
	@DeleteMapping("/{id}")
	public void deleteById(int id) {
		adminService.deleteById(id);
	}

}
