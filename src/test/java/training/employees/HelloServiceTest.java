package training.employees;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelloServiceTest {

    @Test
    void testSayHello() {
        HelloService service = new HelloService();
        String msg = service.sayHello();
        assertTrue(msg.startsWith("Hello Spring"));
    }
}
