package idv.tha101.extractp.web.dao;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import idv.tha101.extractp.base.dao.BaseRepository;
import idv.tha101.extractp.web.pojo.MemberArticleFavVO;

@RepositoryRestResource
@Repository
public interface MemberArticleFavRepository extends BaseRepository<MemberArticleFavVO, MemberArticleFavVO.FavPk> {

	//	以會員Id搜尋收藏資料	
	List<MemberArticleFavVO> findByPkMemberId(Integer memberId);
}
