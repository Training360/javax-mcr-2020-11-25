package training.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeesRepositoryTest {

    @Autowired
    EmployeesRepository employeesRepository;

    @Test
    void testSaveThenList() {
        employeesRepository.save(new Employee("John Doe"));
        employeesRepository.save(new Employee("Jack Doe"));

        List<Employee> employees = employeesRepository.findAll(Sort.by("name")) ;

        assertThat(employees).extracting(Employee::getName)
                .containsExactly("Jack Doe", "John Doe");
    }
}
