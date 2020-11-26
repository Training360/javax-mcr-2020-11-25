package training.employees;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeesService {

    private static final List<Employee> employees = Arrays.asList(
            new Employee(1L, "John Doe"),
            new Employee(2L, "Jack Doe")
    );

    private final ModelMapper modelMapper;

    public List<EmployeeDto> listEmployees() {
        java.lang.reflect.Type targetListType = new TypeToken<List<EmployeeDto>>() {}.getType();
        return modelMapper.map(employees, targetListType);
    }
}
