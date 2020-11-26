package training.employees;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeesController {

    private final EmployeesService employeesService;

    @GetMapping
    public List<EmployeeDto> listEmployees(@RequestParam Optional<String> prefix)  {
        return employeesService.listEmployees(prefix);
    }

    @GetMapping("/{id}")
    public EmployeeDto findEmployeeById(@PathVariable("id") long id) {
        return employeesService.findEmployeeById(id);
    }
}
