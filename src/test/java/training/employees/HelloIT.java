package training.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class HelloIT {

    @Autowired
    HelloController helloController;

    @Test
    void testSayHello() {
        String msg = helloController.sayHello();
        assertTrue(msg.startsWith("Hello Spring Boot World from Service"));
    }
}
