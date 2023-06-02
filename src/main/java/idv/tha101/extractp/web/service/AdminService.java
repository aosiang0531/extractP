package idv.tha101.extractp.web.service;

import idv.tha101.extractp.base.service.BaseService;
import idv.tha101.extractp.web.pojo.AdminVO;

public interface AdminService extends BaseService<AdminVO>{

	AdminVO register(AdminVO vo);

}
