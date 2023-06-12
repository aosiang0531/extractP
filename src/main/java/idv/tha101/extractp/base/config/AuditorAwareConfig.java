package idv.tha101.extractp.base.config;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getCreator", dateTimeProviderRef = "getTime")
public class AuditorAwareConfig {
	@Bean
	public AuditorAware<String> getCreator() {
		return new AuditorAware<String>() {
			@Override
			public Optional<String> getCurrentAuditor() {
				return Optional.of("1");
			}

		};
	}

	@Bean
	public DateTimeProvider getTime() {
		System.out.println(LocalDateTime.now());
		return () -> Optional.of(LocalDateTime.now());
	}
}