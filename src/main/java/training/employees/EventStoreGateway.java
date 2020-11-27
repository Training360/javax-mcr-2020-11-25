package training.employees;

import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;

@Gateway
@AllArgsConstructor
public class EventStoreGateway {

    private final JmsTemplate jmsTemplate;

    public void sendEvent(CreateEventCommand command) {
        jmsTemplate.convertAndSend("eventsQueue",
                command);
    }
}
