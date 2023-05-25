package idv.tha101.extractp.sample.pojo;

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
@Accessors(chain = true, prefix = "sample_")
@Entity
@Table(name = "sample")
public class SampleVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sample_id;

	private String sample_text;
}
