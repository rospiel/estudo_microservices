package estudo.microservices.course;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import estudo.microservices.core.model.Microservice;
import estudo.microservices.course.endpoint.service.MicroserviceService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@EnableAutoConfiguration
public class MicroserviceEndpointTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @MockBean
    private MicroserviceService microserviceService;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(WireMockConfiguration.options().port(2345));

    private HttpEntity<Void> adminHeader;
    private HttpEntity<Void> wrongHeader;
    private StringBuilder url;

    @Before
    public void setup() {
        WireMock.stubFor(WireMock.get("/principal")
                .willReturn(WireMock.okJson("{\"username\":\"rodrigo\",\"authorities\":[{\"authority\":\"ROLE_ADMIN\"}]}")));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp3ayI6eyJrdHkiOiJSU0EiLCJlIjoiQVFBQiIsImtpZCI6IjcyOTUxMWMyLTEwODMtNDY4Mi04YTgyLTVkZWE5Njg4OTgxYiIsIm4iOiJsUzNvRG9oNVBqcmFHYW11MWJ5R3doNkNMRXpheFVoQWk4dmFIV0NDM2dmQ3FfU0p6bkRlZzQ0WW5XT1NPRFVvUDlxbWlQR3dsRHlVOEptQnNPZzl4TF9lb1paM05XdUhteTlVQ1lVNWhKY1FER3dmQlhlYTAwLWgyQWVQcU0wVmhEQlRqTWxMdzBwNXVRYUQ3cVRBNE9HdXFIOEI5UUg1aGJNb3JRVkZBUTEyREhPd01yZEZIak1TQ2poU1pJMjYzc3NjYXU5M1czQmlpcHBCZnZPSGstQ1Nqc2lQNEVKWUxFYTN6OHF3Z19lekZLaXhLYjdKalNiZjFGdUI4Z2Q4bklYajgtdVpJcFQxY2xrVFZqeTl6dF9TNEQwZndXTUlTNXJQYWhxUkIwSHhkLXc3T2JXUHJCUkRSQktCS29XVXRqNU1JQU4zaHFIcnAtMGZ1TWxLUVEifX0.eyJzdWIiOiJyb2RyaWdvIiwiaXNzIjoiaHR0cDpcL1wvIiwiZXhwIjoxNTc4MDg2ODUxLCJpYXQiOjE1NzgwODMyNTEsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iXX0.Y-h9V7-dFpXidhxSDjNKEgbB4F4PFdE1q2TmtFlTBAXF6WxHb_i3gRPx2ZvBHPUOQsoj47fcJ_wzHHZKuL2LJGeKx5hYqdd6BJBclHqGzG-krv0TKgwg6xg0cynQSZATwB3fN6UmOezblcsbXCesIcBBL0g7Qi4I_6f33D394kpql9BQA9v_nJ7h7WiknmYrHPyJ-ohsFc6WSMt3AFBrIk3awB57lL3Ge7v8eldykASeQrKYyzS6ayZyKynpTuqNtPeIK9qtEeYTxDNhTe9M4RvrLjVmf8KsxWXcPF6Zeagj8osnlwHOqVu0KJghhGGR3d5PwFgW_wAiQ_u7bQuyIw");
        this.adminHeader = new HttpEntity<>(headers);
        url = new StringBuilder("http://localhost:" + port + "/v1/admin/microservice");
    }

    @Before
    public void configWrongHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "invalid");
        this.wrongHeader = new HttpEntity<>(headers);
    }

    @Test
    public void listMicroservicesWhenTokenIsIncorrectShouldReturnCode401() {
        ResponseEntity<String> responseEntity = testRestTemplate.exchange(url.toString(), HttpMethod.GET, wrongHeader, String.class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(401);
    }

    @Test
    public void listMicroservicesWhenUsernameAndPasswordAreCorrectShouldReturnCode200() {
        Microservice microservice = new Microservice(10L, "Gerente");
        List<Microservice> microservices = new ArrayList<>();
        microservices.add(microservice);
        BDDMockito.when(microserviceService.list(BDDMockito.any(PageRequest.class))).thenReturn(microservices);
        ResponseEntity<String> response = testRestTemplate.exchange(url.toString(), HttpMethod.GET, adminHeader, String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
}

