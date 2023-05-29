package idv.tha101.extractp.web.service;

import java.util.Collection;
import java.util.List;

import idv.tha101.extractp.base.service.BaseService;
import idv.tha101.extractp.web.pojo.ArticleDTO;
import idv.tha101.extractp.web.pojo.ArticleVO;

public interface ArticleService extends BaseService<ArticleVO>{
	
//	List<ArticleVO> findByMemberId(Integer memberId);
	
	Collection<ArticleDTO> findPopArticle();
	
	Collection<ArticleDTO> findLatestArticle();
	
//	Collection<ArticleDTO> findTemLatestArticle();
}
