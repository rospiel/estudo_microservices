package estudo.microservices.course.repository;

import estudo.microservices.course.model.Microservice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MicroserviceRepository extends PagingAndSortingRepository<Microservice, Long> {
}
