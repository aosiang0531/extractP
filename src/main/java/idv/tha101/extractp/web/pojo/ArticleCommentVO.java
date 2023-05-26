package idv.tha101.extractp.web.pojo;

import java.io.Serializable;
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
@Accessors(chain = true, prefix = "article_comment_")
@Entity
@Table(name = "article_comment", catalog = "THA101_G7")
public class ArticleCommentVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer article_comment_id;
	@Column
	private Integer member_id;
	@Column
	private Integer article_id;
	@Column
	private String article_comment_content;
	@Column
	private Timestamp article_comment_created_time;
	
//	@ManyToOne
//	@JoinColumn(name = "article_id",insertable = false, updatable = false)
//	private Article article;
//	
//	@OneToMany(mappedBy = "article_comment_id")
//	private List<ArticleCommentReport> articleCommentReports;
	
}
