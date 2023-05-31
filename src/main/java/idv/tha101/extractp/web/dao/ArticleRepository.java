package idv.tha101.extractp.web.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import idv.tha101.extractp.base.dao.BaseRepository;
import idv.tha101.extractp.web.pojo.ArticleDTO;
import idv.tha101.extractp.web.pojo.ArticleVO;

@RepositoryRestResource
@Repository
public interface ArticleRepository extends BaseRepository<ArticleVO, Integer> {

	// 找特定作者文章
	List<ArticleVO> findByMemberId(Integer memberId);
	
	
	// 找熱門文章
	@Query(value = "select m.member_created_user,\r\n"
			+ "	   g.article_group_name, \r\n"
			+ "       a.article_title, \r\n"
			+ "       left(a.article_content, 55) 'article_content', \r\n"
			+ "	   a.article_image, \r\n"
			+ "       a.article_created_date, \r\n"
			+ "       a.article_thunmb_number,\r\n"
			+ "       a.article_comment_number, \r\n"
			+ "       a.member_article_fav_number \r\n"
			+ "from article a \r\n"
			+ "join article_group g\r\n"
			+ "	on a.article_group_id = g.article_group_id\r\n"
			+ "join MEMBER m\r\n"
			+ "	on a.member_id = m.member_id\r\n"
			+ "order by (article_thunmb_number + article_comment_number + member_article_fav_number) desc;", nativeQuery = true)
	Collection<ArticleDTO> findPopArticle();
	
	// 找最新文章
	@Query(value = "select m.member_created_user, \r\n"
			+ "	   g.article_group_name,\r\n"
			+ "	   a.article_title, \r\n"
			+ "       left(a.article_content, 55) 'article_content', \r\n"
			+ "	   a.article_image, \r\n"
			+ "       a.article_created_date, \r\n"
			+ "       a.article_thunmb_number,\r\n"
			+ "       a.article_comment_number, \r\n"
			+ "       a.member_article_fav_number\r\n"
			+ "from article a \r\n"
			+ "join article_group g\r\n"
			+ "	on a.article_group_id = g.article_group_id\r\n"
			+ "join MEMBER m\r\n"
			+ "	on a.member_id = m.member_id\r\n"
			+ "order by article_created_date desc;", nativeQuery = true)
	Collection<ArticleDTO> findLatestArticle();
	
//	// 找特定板塊文章
//	@Query(value = "select m.member_created_user, \r\n"
//			+ "	   g.article_group_name,\r\n"
//			+ "	   a.article_title, \r\n"
//			+ "       left(a.article_content, 55), \r\n"
//			+ "	   a.article_image, \r\n"
//			+ "       a.article_created_date, \r\n"
//			+ "       a.article_thunmb_number,\r\n"
//			+ "       a.article_comment_number, \r\n"
//			+ "       a.member_article_fav_number\r\n"
//			+ "from article_group g\r\n"
//			+ "join article a\r\n"
//			+ "	on g.article_group_id = a.article_group_id\r\n"
//			+ "join Member m\r\n"
//			+ "	on a.member_id = m.member_id\r\n"
//			+ "where g.article_template_id = 3\r\n"
//			+ "order by (article_thunmb_number + article_comment_number + member_article_fav_number) desc;", nativeQuery = true)
//	Collection<ArticleDTO> findTemPopArticle();
	
//	@Query("select m.member_created_user, \r\n"
//			+ "	   article_title, \r\n"
//			+ "       article_content, \r\n"
//			+ "       article_image, \r\n"
//			+ "       article_created_date, \r\n"
//			+ "       article_thunmb_number, \r\n"
//			+ "       article_comment_number, \r\n"
//			+ "       member_article_fav_number\r\n"
//			+ "from article a\r\n"
//			+ "join MEMBER m\r\n"
//			+ "	on a.member_id = m.member_id\r\n"
//			+ "where a.article_id = 1;")
//	ArticleVO getArticleJoinMember();
	

}
