package idv.tha101.extractp.web.dao;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import idv.tha101.extractp.base.dao.BaseRepository;
import idv.tha101.extractp.sample.pojo.SampleVO;
import idv.tha101.extractp.web.pojo.ArticleCommentReportVO;

@RepositoryRestResource
@Repository
public interface ArticleCommentReportRepository extends BaseRepository<SampleVO, Integer> {

	ArticleCommentReportVO getByArticleCommentReportStatus(Boolean article_comment_report_status);


}
