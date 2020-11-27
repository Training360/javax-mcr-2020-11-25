package training.employees;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@EnableConfigurationProperties(MessagesProperties.class)
public class HelloService {

    private String welcomeMessage;

    private MessagesProperties messagesProperties;

    public HelloService(@Value("${employees.welcome.message}") String welcomeMessage, MessagesProperties messagesProperties) {
        this.welcomeMessage = welcomeMessage;
        this.messagesProperties = messagesProperties;
    }

    public String sayHello() {
        return welcomeMessage + messagesProperties.getMessage1() + LocalDateTime.now();
    }
}
