package idv.tha101.extractp.web.pojo;

import java.sql.Timestamp;

public interface ArticleCommentDTO {

	Integer getarticle_comment_id();
	String getmember_name();
	byte[] getmember_image();
	String getarticle_comment_content();
	Timestamp getarticle_comment_created_date();
	Boolean getarticle_comment_is_hidden();
}
