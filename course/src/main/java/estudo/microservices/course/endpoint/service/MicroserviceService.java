package estudo.microservices.course.endpoint.service;

import estudo.microservices.course.model.Microservice;
import estudo.microservices.course.repository.MicroserviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MicroserviceService {
    private final MicroserviceRepository microserviceRepository;

    public Iterable<Microservice> list (Pageable pageable) {
        log.info("Listing all microservices");
        return microserviceRepository.findAll(pageable);
    }
}
