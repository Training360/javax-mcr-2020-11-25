package training.employees;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class EmployeesService {

    private final EmployeesRepository employeesRepository;

    private final ModelMapper modelMapper;

    private final AddressesGateway addressesGateway;

    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
        log.info("List employees");
        log.debug("List employees - prefix is: " + prefix);

        java.lang.reflect.Type targetListType = new TypeToken<List<EmployeeDto>>() {}.getType();
        return modelMapper.map(
                employeesRepository.findAll()
                , targetListType);
    }

    public EmployeeDto findEmployeeById(long id) {
        EmployeeDto employeeDto = modelMapper.map(employeesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"))
                , EmployeeDto.class);

        employeeDto.setAddressDto(addressesGateway.getAddress(employeeDto.getName()));

        return employeeDto;
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        Employee employee = new Employee(command.getName());
        employeesRepository.save(employee);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Transactional
    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        Employee employee = employeesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
        employee.setName(command.getName());

        return modelMapper.map(employee, EmployeeDto.class);
    }

    public void deleteEmployee(long id) {
        employeesRepository.deleteById(id);
    }

    public void clearAll() {
        employeesRepository.deleteAll();
    }
}
