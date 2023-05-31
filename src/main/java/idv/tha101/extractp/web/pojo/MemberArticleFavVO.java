package idv.tha101.extractp.web.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@Accessors(chain = true, prefix = "member_article_fav_")
@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
@Table(name = "member_article_fav")
public class MemberArticleFavVO {

	@EmbeddedId
	private PK pk;

	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp member_article_fav_created_date;

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
