package idv.tha101.extractp.web.pojo;

import java.sql.Timestamp;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
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
@Accessors(chain = true, prefix = "article_")
@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
@Table(name = "article")
public class ArticleVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer article_id;

	private Integer member_id;

	private Integer article_group_id;

	private String article_title;

	private String article_content;

	private byte[] article_image;

	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp article_created_date;

	@LastModifiedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp article_last_modified_date;

	@LastModifiedBy
	private String article_last_modified_by;

	@Column(insertable = false, updatable = false)
	private Integer article_thunmb_number;
	@Column(insertable = false, updatable = false)
	private Integer article_comment_number;
	@Column(insertable = false, updatable = false)
	private Integer member_article_fav_number;
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
