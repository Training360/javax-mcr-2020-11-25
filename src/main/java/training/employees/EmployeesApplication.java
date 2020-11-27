package training.employees;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import liquibase.pro.packaged.B;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.boot.actuate.audit.InMemoryAuditEventRepository;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class EmployeesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeesApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper().findAndRegisterModules();
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Employees API")
						.version("1.0.0")
						.description("Operations with employees"));
	}

	@Bean
	public MessageConverter messageConverter(ObjectMapper objectMapper){
		MappingJackson2MessageConverter converter =
				new MappingJackson2MessageConverter();
		converter.setTypeIdPropertyName("_typeId");
		Map map = new HashMap();
		map.put("CreateEventCommand", CreateEventCommand.class);
		converter.setTypeIdMappings(map);
		return converter;
	}

	@Bean
	public HttpTraceRepository httpTraceRepository() {
		return new InMemoryHttpTraceRepository();
	}

	@Bean
	public AuditEventRepository auditEventRepository() {
		return new InMemoryAuditEventRepository();
	}

}
