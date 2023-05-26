package idv.tha101.extractp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ExtractpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExtractpApplication.class, args);
	}

}
