package idv.tha101.extractp.web.service;

import java.util.List;

import idv.tha101.extractp.base.service.BaseService;
import idv.tha101.extractp.web.pojo.ArticleCommentDTO;
import idv.tha101.extractp.web.pojo.ArticleCommentVO;

public interface ArticleCommentService extends BaseService<ArticleCommentVO>{

	List<ArticleCommentDTO> findByArticleId(Integer id);
}
