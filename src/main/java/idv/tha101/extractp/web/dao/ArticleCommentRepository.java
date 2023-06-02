package idv.tha101.extractp.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import idv.tha101.extractp.base.dao.BaseRepository;
import idv.tha101.extractp.web.pojo.ArticleCommentDTO;
import idv.tha101.extractp.web.pojo.ArticleCommentVO;

@RepositoryRestResource
@Repository
public interface ArticleCommentRepository extends BaseRepository<ArticleCommentVO, Integer> {

	// 顯示特定文章之留言
	@Query(value = "select "
			+ " m.member_created_user, \r\n"
			+ "	   c.article_comment_id, \r\n"
			+ "	   c.article_comment_content, \r\n"
			+ "       c.article_comment_created_date\r\n"
			+ "from article a \r\n"
			+ "join article_comment c\r\n"
			+ "on a.article_id = c.article_id\r\n"
			+ "join MEMBER m\r\n"
			+ "on a.member_id = m.member_id\r\n"
			+ "where a.article_id = ?;", nativeQuery = true)
	List<ArticleCommentDTO> findByArticleId(Integer id);
	
}
