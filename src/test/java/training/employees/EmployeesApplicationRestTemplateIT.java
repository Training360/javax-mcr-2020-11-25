package training.employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeesApplicationRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Autowired
    EmployeesService employeesService;

    @BeforeEach
    void init() {
        employeesService.clearAll();
    }

    @Test
    void create() {
        template.exchange("/api/employees",
                HttpMethod.POST,
                new HttpEntity<>(new CreateEmployeeCommand("John Test Doe")),
                EmployeeDto.class);

        template.exchange("/api/employees",
                HttpMethod.POST,
                new HttpEntity<>(new CreateEmployeeCommand("Jack Test Doe")),
                EmployeeDto.class);

        List<EmployeeDto> employees = template.exchange("/api/employees",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EmployeeDto>>(){}).getBody();

        assertThat(employees).extracting("name")
                .containsExactly("John Test Doe", "Jack Test Doe");
    }


}
