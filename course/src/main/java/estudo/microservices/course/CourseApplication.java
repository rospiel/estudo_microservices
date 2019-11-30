package estudo.microservices.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * EntityScan --> where find model
 * EnableJpaRepositories --> where find repository
 */

@SpringBootApplication
@EntityScan({"estudo.microservices.core.model"})
@EnableJpaRepositories({"estudo.microservices.core.repository"})
public class CourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }

}
