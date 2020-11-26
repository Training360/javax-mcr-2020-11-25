package training.employees;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeesService {

    private static final List<Employee> employees = Arrays.asList(
            new Employee(1L, "John Doe"),
            new Employee(2L, "Jack Doe")
    );

    private final ModelMapper modelMapper;

    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
        java.lang.reflect.Type targetListType = new TypeToken<List<EmployeeDto>>() {}.getType();
        return modelMapper.map(
                employees.stream()
                    .filter(e -> !prefix.isPresent() ||
                            e.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
                .collect(Collectors.toList())
                , targetListType);
    }

    public EmployeeDto findEmployeeById(long id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .map(e -> modelMapper.map(e, EmployeeDto.class))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Employee has not found with id" + id));
    }
}
