package training.employees;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
@AllArgsConstructor
public class EmployeesRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Employee> listEmployees() {
        List<Employee> employees = jdbcTemplate.query(
                "select id, emp_name from employees",
                this::convertEmployee);
        return employees;
    }

    public Employee findById(long id) {
        return jdbcTemplate.queryForObject("select id, emp_name from employees where id = ?",
            this::convertEmployee,
                id);
    }

    public void createEmployee(Employee employee) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                con -> {
                    PreparedStatement ps =
                            con.prepareStatement("insert into employees(emp_name) values (?)",
                                    Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, employee.getName());
                    return ps;
                }, keyHolder);
        employee.setId(keyHolder.getKey().longValue());
    }

    public void updateEmployee(Employee employee) {
        jdbcTemplate.update(
                "update employees set emp_name = ? where id = ?", employee.getName(),
                employee.getId());
    }

    public void deleteEmployee(long id) {
        jdbcTemplate.update(
                "delete from employees where id = ?", id);
    }

    private Employee convertEmployee(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("emp_name");
        Employee employee = new Employee(id, name);
        return employee;
    }

    public void deleteAllEmployee() {
        jdbcTemplate.update(
                "delete from employees");
    }
}
