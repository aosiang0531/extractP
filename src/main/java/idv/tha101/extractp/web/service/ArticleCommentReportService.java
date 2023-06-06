package idv.tha101.extractp.web.service;

import java.util.List;

import idv.tha101.extractp.base.service.BaseService;
import idv.tha101.extractp.web.pojo.ArticleCommentReportVO;
import idv.tha101.extractp.web.pojo.ArticleReportVO;

public interface ArticleCommentReportService extends BaseService<ArticleCommentReportVO> {

	List<ArticleCommentReportVO> findByArticleCommentReportStatus(String status);
}
