package idv.tha101.extractp.web.dao;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import idv.tha101.extractp.base.dao.BaseRepository;
import idv.tha101.extractp.web.pojo.ArticleCommentReportVO;

@RepositoryRestResource
@Repository
public interface ArticleCommentReportRepository extends BaseRepository<ArticleCommentReportVO, Integer> {

	List<ArticleCommentReportVO> findByArticleCommentReportStatus(String status);
}
