package idv.tha101.extractp.web.pojo;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
@Table(name = "article_hastag", catalog = "THA101_G7")
public class ArticleHastagVO{

	@EmbeddedId
	private PK pk;
	@Column
	private Timestamp article_hastag_created_time;
	@Column
	private Timestamp article_hastag_last_modified_time;
	@Column
	private String article_hastag_created_member;
	@Column
	private String article_hastag_last_modified_member;

//	@ManyToOne
//	@JoinColumn(name = "article_tag_id", insertable = false, updatable = false)
//	private ArticleTag articleTag;
//
//	@ManyToOne
//	@JoinColumn(name = "article_id", insertable = false, updatable = false)
//	private Article article;

	@Embeddable
	static class PK implements Serializable {

		private static final long serialVersionUID = 1L;

		@Column
		private Integer article_id;
		@Column
		private Integer article_tag_id;

		@Override
		public int hashCode() {
			return Objects.hash(article_id, article_tag_id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			PK other = (PK) obj;
			return Objects.equals(article_id, other.article_id) && Objects.equals(article_tag_id, other.article_tag_id);
		}
	}
}
