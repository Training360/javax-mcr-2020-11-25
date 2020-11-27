package training.employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from employees")
public class EmployeesApplicationRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Autowired
    EmployeesService employeesService;

    @MockBean
    AddressesGateway addressesGateway;

//    @BeforeEach
//    void init() {
//        employeesService.clearAll();
//    }

    @RepeatedTest(2)
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

    @Test
    void findById() {
        EmployeeDto result = template.exchange("/api/employees",
                HttpMethod.POST,
                new HttpEntity<>(new CreateEmployeeCommand("John Test Doe")),
                EmployeeDto.class).getBody();

        long id = result.getId();

        EmployeeDto employee = template.exchange("/api/employees/" + id,
                HttpMethod.GET,
                null,
                EmployeeDto.class).getBody();

        verify(addressesGateway).getAddress(eq("John Test Doe"));

        assertEquals("John Test Doe", employee.getName());
    }


}
