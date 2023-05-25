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
@Accessors(chain = true, prefix = "article_template_")
@Entity
@Table(name = "article_template", catalog = "THA101_G7")
public class ArticleTemplateVO{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer article_template_id;
	@Column
	private String article_template_name;
	@Column
	private String article_template_description;
	@Column(updatable = false)
	private Timestamp article_template_created_time;
	@Column(insertable = false)
	private Timestamp article_template_last_modified_time;
	@Column(updatable = false)
	private String article_template_created_member;
	@Column(insertable = false)
	private String article_template_last_modified_member;

	
//	@OneToMany(mappedBy = "article_template_id")
//	private List<ArticleGroup> articleGroup;

}
