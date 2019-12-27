package estudo.microservices.course;

import estudo.microservices.core.property.JwtConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * EntityScan --> where find model
 * EnableJpaRepositories --> where find repository
 */

@SpringBootApplication
@EntityScan({"estudo.microservices.core.model"})
@EnableJpaRepositories({"estudo.microservices.core.repository"})
@EnableConfigurationProperties(value = JwtConfiguration.class)
@ComponentScan("estudo.microservices")
public class CourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }

}
