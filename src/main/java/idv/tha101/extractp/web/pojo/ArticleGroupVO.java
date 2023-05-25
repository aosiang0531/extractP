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
@Accessors(chain = true, prefix = "article_group_")
@Entity
@Table(name = "article_group", catalog = "THA101_G7")
public class ArticleGroupVO{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer article_group_id;
	@Column
	private Integer article_template_id;
	@Column
	private String article_group_name;
	@Column
	private Boolean article_group_is_hidden;
	@Column(updatable = false)
	private Timestamp article_group_created_time;
	@Column(insertable = false)
	private Timestamp article_group_last_modified_time;
	@Column(updatable = false)
	private String article_group_created_member;
	@Column(insertable = false)
	private String article_group_last_modified_member;
//	
//	@ManyToOne
//	@JoinColumn(name = "article_template_id",insertable = false, updatable = false)
//	private ArticleTemplate articleTemplate;
//	
//	@OneToMany(mappedBy = "article_group_id")
//	private List<Article> articles;
	
	
}
