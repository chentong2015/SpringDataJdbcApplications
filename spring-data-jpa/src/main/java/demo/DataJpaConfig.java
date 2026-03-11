package demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// Enable JPA repositories.
// Will scan the package of the annotated configuration class for Spring Data repositories by default.
@EnableJpaRepositories
@Configuration
public class DataJpaConfig {

}
