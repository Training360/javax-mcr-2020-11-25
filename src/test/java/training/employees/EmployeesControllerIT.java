package training.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = EmployeesController.class)
public class EmployeesControllerIT {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmployeesService employeesService;

    @Test
    void testList() throws Exception {
        when(employeesService.listEmployees(any())).thenReturn(Arrays.asList(
                new EmployeeDto(1L, "John Test Doe"),
                new EmployeeDto(2L, "Jack Test Doe")
        ));

        mockMvc.perform(get("/api/employees"))
//                .andDo(print())
                .andExpect(jsonPath("[1].name", equalTo("Jack Test Doe")))
                .andExpect(status().isOk());
    }

}
