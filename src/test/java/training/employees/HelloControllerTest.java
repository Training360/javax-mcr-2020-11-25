package training.employees;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HelloControllerTest {

    @Mock
    HelloService helloService;

    @InjectMocks
    HelloController helloController;

    @Test
    void testSayHello() {
        helloController.sayHello();
        verify(helloService, times(1)).sayHello();
    }

    @Test
    void testSayHelloReturn() {
        when(helloService.sayHello()).thenReturn("test");

        String msg = helloController.sayHello();
        assertEquals("test", msg);
    }
}
