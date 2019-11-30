package estudo.microservices.core.repository;

import estudo.microservices.core.model.Microservice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MicroserviceRepository extends PagingAndSortingRepository<Microservice, Long> {
}
