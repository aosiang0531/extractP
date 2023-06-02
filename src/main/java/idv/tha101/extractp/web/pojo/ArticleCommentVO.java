package idv.tha101.extractp.web.pojo;

import java.sql.Timestamp;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@Accessors(chain = true, prefix = "article_comment_")
@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
@Table(name = "article_comment")
public class ArticleCommentVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer article_comment_id;

	@Column(name = "member_id")
	private Integer article_comment_member_id;

	@Column(name = "article_id")
	private Integer article_comment_article_id;

	private String article_comment_content;

	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp article_comment_created_date;

//	@ManyToOne
//	@JoinColumn(name = "article_id",insertable = false, updatable = false)
//	private Article article;
//	
//	@OneToMany(mappedBy = "article_comment_id")
//	private List<ArticleCommentReport> articleCommentReports;

}
