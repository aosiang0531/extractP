package idv.tha101.extractp.web.pojo;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true, prefix = "article_comment_repor_")
@Entity
@Table(name = "article_comment_report", catalog = "THA101_G7")
public class ArticleCommentReportVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer article_comment_report_id;
	@Column
	private Integer member_id;
	@Column
	private Integer article_comment_id;
	@Column
	private String article_comment_report_content;
	@Column(updatable = false)
	private Timestamp article_comment_report_created_time;
	@Column
	private Boolean article_comment_report_status;
	@Column(insertable = false)
	private Timestamp article_comment_report_last_modified_time;
	@Column(insertable = false)
	private String article_comment_report_last_modified_member;
	
//	@ManyToOne
//	@JoinColumn(name = "article_comment_id",insertable = false, updatable = false)
//	private ArticleComment articleComment;
	
}
