package training.employees;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties(prefix = "employees")
@Data
@Validated
public class MessagesProperties {

    private String message1;
    private String message2;
    private String message3;

    @NotBlank
    private String message4;
}
