package idv.tha101.extractp.web.pojo;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
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
import jakarta.persistence.OneToMany;
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
@Accessors(chain = true, prefix = "article_group_")
@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
@Table(name = "article_group")
public class ArticleGroupVO{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer article_group_id;

	@Column(name = "article_template_id")
	private Integer article_group_article_template_id;

	private String article_group_name;

	private Boolean article_group_is_hidden;

	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp article_group_created_date;

	@LastModifiedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp article_group_last_modified_date;

	@CreatedBy
	private String article_group_created_by;

	@LastModifiedBy
	private String article_group_last_modified_by;
	
	//	
//	@ManyToOne
//	@JoinColumn(name = "article_template_id",insertable = false, updatable = false)
//	private ArticleTemplate articleTemplate;
//	
//	@OneToMany(mappedBy = "article_group_id")
//	private List<ArticleVO> articles;
	
	
}
