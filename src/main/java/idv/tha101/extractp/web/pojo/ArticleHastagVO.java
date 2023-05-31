package idv.tha101.extractp.web.pojo;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
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
@Accessors(chain = true, prefix = "article_hastag_")
@Entity
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
@Table(name = "article_hastag")
public class ArticleHastagVO{

	@EmbeddedId
	private PK pk;
	
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp article_hastag_created_date;

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp article_hastag_last_modified_date;
    
    @CreatedBy
    private String article_hastag_created_by;

    @LastModifiedBy
    private String article_hastag_last_modified_by;

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
