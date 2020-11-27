package training.employees;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelloServiceTest {

    @Test
    void testSayHello() {
        MessagesProperties messagesProperties = new MessagesProperties();
        messagesProperties.setMessage1("confprop");
        HelloService service = new HelloService("Hello Springconfprop", messagesProperties);
        String msg = service.sayHello();
        assertTrue(msg.startsWith("Hello Spring"));
    }
}
