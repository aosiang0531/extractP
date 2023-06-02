package idv.tha101.extractp.web.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.admin.SpringApplicationAdminMXBean;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.AdminRepostiory;
import idv.tha101.extractp.web.pojo.AdminVO;
import idv.tha101.extractp.web.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepostiory adminRepostiory;

	@Override
	public List<AdminVO> findAll() {

		return adminRepostiory.findAll();
	}

	@Override
	public AdminVO findById(Integer id) {
		return adminRepostiory.findById(id).orElseThrow();
	}

	@Override
	public AdminVO saveOrUpdate(AdminVO vo) {
		if (vo.getId() != null) {
			Optional<AdminVO> optionalVO = adminRepostiory.findById(vo.getId());
			if (optionalVO.isPresent()) {
				AdminVO existingVO = optionalVO.get();

				Class<?> voClass = AdminVO.class;
				Field[] fields = voClass.getDeclaredFields();

				for (Field field : fields) {
					field.setAccessible(true);
					try {
						Object updatedValue = field.get(vo);
						if (updatedValue != null) {
							field.set(existingVO, updatedValue);
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}

				return adminRepostiory.save(existingVO);
			} else {
				return null;
			}
		} else {
			return adminRepostiory.save(vo);
		}
	}

	@Override
	public void deleteById(Integer id) {
		adminRepostiory.deleteById(id);
	}

	public AdminVO register(AdminVO adminVO) {

		if (!adminRepostiory.existsByEmail(adminVO.getEmail())) {
			return adminRepostiory.save(adminVO);

		} else {
			return null;
		}

	}
}
