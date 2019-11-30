package estudo.microservices.course.endpoint.controller;

import estudo.microservices.course.endpoint.service.MicroserviceService;
import estudo.microservices.course.model.Microservice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("v1/admin/microservice")
public class MicroserviceController {

    private final MicroserviceService microserviceService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Iterable<Microservice>> list(Pageable pageable) {
        return new ResponseEntity<>(microserviceService.list(pageable), HttpStatus.OK);
    }
}
