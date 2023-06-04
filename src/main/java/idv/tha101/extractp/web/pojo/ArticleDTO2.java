package idv.tha101.extractp.web.pojo;

import java.sql.Timestamp;

public interface ArticleDTO2 {

	Integer getarticle_id();
	String getmember_name();
	String getarticle_title();
	String getarticle_content();
	byte[] getarticle_image();
	Timestamp getarticle_created_date();
	Integer getarticle_thunmb_number();
	Integer getarticle_comment_number();
	Integer getmember_article_fav_number();
}
