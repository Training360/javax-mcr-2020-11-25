package training.employees;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Gateway
public class AddressesGateway {

    private final String url;

    private final RestTemplate restTemplate;

    public AddressesGateway(@Value("${employees.addresses.url}") String url,
                            RestTemplateBuilder restTemplateBuilder) {
        this.url = url;
        restTemplate = restTemplateBuilder.build();
    }

    public AddressDto getAddress(String name) {
        return restTemplate.getForObject(url, AddressDto.class, name);
    }
}
