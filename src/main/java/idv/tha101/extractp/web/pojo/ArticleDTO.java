package idv.tha101.extractp.web.pojo;

import java.sql.Timestamp;

public interface ArticleDTO {
	
	String getmember_created_user();
	String getarticle_group_name();
	String getarticle_title();
	String getarticle_content();
	String getarticle_image();
	Timestamp getarticle_created_date();
	Integer getarticle_thunmb_number();
	Integer getarticle_comment_number();
	Integer getmember_article_fav_number();
	
}