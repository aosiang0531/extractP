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
@Accessors(chain = true, prefix = "article_")
@Entity
@Table(name = "article", catalog = "THA101_G7")
public class ArticleVO{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer article_id;
	@Column
	private Integer member_id;
	@Column
	private Integer article_group_id;
	@Column
	private String article_title;
	@Column
	private String article_content;
	@Column
	private byte[] article_image;
	@Column(updatable = false)
	private Timestamp article_create_time;
	@Column(insertable = false, updatable = false)
	private Integer article_thunmb_number;
	@Column(insertable = false, updatable = false)
	private Integer article_comment_number;
	@Column(insertable = false, updatable = false)
	private Integer member_article_fav_number;
	@Column(insertable = false)
	private String article_last_modified_member;
	@Column(insertable = false)
	private Timestamp article_last_modified_time;
	@Column(insertable = false)
	private Boolean article_is_hidden;
	@Column(insertable = false)
	private Boolean article_is_top;;
	
//	@ManyToOne
//	@JoinColumn(name = "article_group_id",insertable = false, updatable = false)
//	private ArticleGroup articleGroup;
//	
//	
//	@OneToMany(mappedBy = "article_id")
//	private List<ArticleHastag> articleHastags;
//	
//	@OneToMany(mappedBy = "article_id")
//	private List<ArticleComment> articleComments;
//	
//	@OneToMany(mappedBy = "article_id")
//	private List<ArticleReport> artiicleReports;
//	
//	@OneToMany(mappedBy = "article_id")
//	private List<MemberArticleFav> memberArticleFavs;
//	
//	@OneToMany(mappedBy = "article_id")
//	private List<ArticleThunmb> articleThunmbs;
	
	
}
