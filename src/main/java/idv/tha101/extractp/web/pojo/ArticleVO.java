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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	@Column(name = "member_id")
	private Integer memberId;

	private Integer article_group_id;

	private String article_title;

	private String article_content;

	private String article_image;

	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp article_created_date;

	@LastModifiedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp article_last_modified_date;

	@LastModifiedBy
	private String article_last_modified_by;

	
	private Integer article_thunmb_number;
	
	private Integer article_comment_number;
	
	@Column(name = "member_article_fav_number")
	private Integer article_member_article_fav_number;
	
	private Boolean article_is_hidden;
	
	private Boolean article_is_top;;
	
	
	public Integer getMember_id() {
	    return memberId;
	}

	public void setMember_id(Integer member_id) {
	    this.memberId = member_id;
	}
	

//	@ManyToOne
//	@JoinColumn(name = "article_group_id",insertable = false, updatable = false)
//	private ArticleGroupVO articleGroup;
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
