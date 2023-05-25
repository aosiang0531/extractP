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
@Accessors(chain = true, prefix = "article_tag_")
@Entity
@Table(name = "article_tag", catalog = "THA101_G7")
public class ArticleTagVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer article_tag_id;
	@Column
	private String article_tag_name;
	@Column
	private Timestamp article_tag_created_time;
	@Column
	private String article_tag_created_member;
	
//	@OneToMany(mappedBy = "article_tag_id")
//	private List<ArticleHastag> articleHastags;
	
}
