package estudo.microservices.course.endpoint.controller;

import estudo.microservices.core.model.Microservice;
import estudo.microservices.course.endpoint.service.MicroserviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "Endpoints to manage micro service")
public class MicroserviceController {

    private final MicroserviceService microserviceService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "List all micro services", response = Microservice[].class)
    public ResponseEntity<Iterable<Microservice>> list(Pageable pageable) {
        Iterable<Microservice> list = microserviceService.list(pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
