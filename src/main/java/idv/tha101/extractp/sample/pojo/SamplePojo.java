package idv.tha101.extractp.sample.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sample")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SamplePojo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sample_id;
	
	private String sample_text;
}
