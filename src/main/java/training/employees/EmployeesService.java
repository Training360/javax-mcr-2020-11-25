package training.employees;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeesService {

    private final EmployeesRepository employeesRepository;

    private final ModelMapper modelMapper;

    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
        log.info("List employees");
        log.debug("List employees - prefix is: " + prefix);

        java.lang.reflect.Type targetListType = new TypeToken<List<EmployeeDto>>() {}.getType();
        return modelMapper.map(
                employeesRepository.listEmployees()
                , targetListType);
    }

    public EmployeeDto findEmployeeById(long id) {
        return modelMapper.map(employeesRepository.findById(id), EmployeeDto.class);
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        Employee employee = new Employee(command.getName());
        employeesRepository.createEmployee(employee);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        Employee employee = new Employee(id, command.getName());
        employeesRepository.updateEmployee(employee);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public void deleteEmployee(long id) {
        employeesRepository.deleteEmployee(id);
    }

    public void clearAll() {
        employeesRepository.deleteAllEmployee();
    }
}
