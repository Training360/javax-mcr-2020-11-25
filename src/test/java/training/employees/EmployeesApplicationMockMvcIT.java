package training.employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class EmployeesApplicationMockMvcIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    EmployeesService employeesService;

    @BeforeEach
    void init() {
        employeesService.clearAll();
    }

    @RepeatedTest(2)
    void testCreate() throws Exception {
        mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Jane Doe\"}"))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/employees"))
                .andExpect(jsonPath("$.length()", equalTo(1)));
    }


}
