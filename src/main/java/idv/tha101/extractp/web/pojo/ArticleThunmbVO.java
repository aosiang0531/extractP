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
@Accessors(chain = true, prefix = "article_thunmb_")
@Entity
@Table(name = "article_thunmb", catalog = "THA101_G7")
public class ArticleThunmbVO {

	@EmbeddedId
	private PK pk;
	@Column
	private Timestamp article_thunmb_created_time;

//	@ManyToOne
//	@JoinColumn(name = "article_id", insertable = false, updatable = false)
//	private Article article;

	@Embeddable
	static class PK implements Serializable {
		private static final long serialVersionUID = 1L;

		@Column
		private Integer member_id;
		@Column
		private Integer article_id;

		@Override
		public int hashCode() {
			return Objects.hash(article_id, member_id);
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
			return Objects.equals(article_id, other.article_id) && Objects.equals(member_id, other.member_id);
		}

	}
}
