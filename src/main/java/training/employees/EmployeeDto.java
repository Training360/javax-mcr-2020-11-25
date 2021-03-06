package training.employees;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class EmployeeDto {

    @Schema(description="id of the employee", example = "1")
    private Long id;

    @Schema(description="name of the employee", example = "John Doe")
    private String name;

    private AddressDto addressDto;
}
