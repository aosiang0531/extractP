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
@Accessors(chain = true, prefix = "artiicle_report_")
@Entity
@Table(name = "artiicle_report", catalog = "THA101_G7")
public class ArticleReportVO{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer artiicle_report_id;
	@Column
	private Integer member_id;
	@Column
	private Integer article_id;
	@Column
	private String artiicle_report_content;
	@Column(updatable = false)
	private Timestamp artiicle_report_created_time;
	@Column
	private Boolean artiicle_report_status;
	@Column(insertable = false)
	private Timestamp artiicle_report_last_modified_time;
	@Column(insertable = false)
	private String artiicle_report_last_modified_member;
	
//	@ManyToOne
//	@JoinColumn(name = "article_id",insertable = false, updatable = false)
//	private Article article;
}
