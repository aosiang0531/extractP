package idv.tha101.extractp.web.service;

import idv.tha101.extractp.base.service.BaseService;
import idv.tha101.extractp.web.pojo.MemberArticleFavVO;
import idv.tha101.extractp.web.pojo.MemberArticleFavVO.FavPk;

public interface MemberArticleFavService extends BaseService<MemberArticleFavVO> {

	public void deleteFav(FavPk pk);
}
