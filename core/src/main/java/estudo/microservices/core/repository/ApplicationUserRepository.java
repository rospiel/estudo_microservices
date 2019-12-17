package estudo.microservices.core.repository;

import estudo.microservices.core.model.ApplicationUser;
import estudo.microservices.core.model.Microservice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApplicationUserRepository extends PagingAndSortingRepository<ApplicationUser, Long> {

    public ApplicationUser findByUsername(String username);

}
