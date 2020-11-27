package training.employees;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.util.SocketUtils;

import java.util.concurrent.TimeUnit;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressGatewayTest {

    static String host = "127.0.0.1";
    static int port;
    static WireMockServer wireMockServer;

    @BeforeAll
    static void startServer() {
        port = SocketUtils.findAvailableTcpPort();
        wireMockServer = new WireMockServer(wireMockConfig().port(port));
        WireMock.configureFor(host, port);
        wireMockServer.start();
    }

    @AfterAll
    static void stopServer() {
        wireMockServer.stop();
    }
    @BeforeEach
    void resetServer() {
        WireMock.reset();
    }

    @Test
    // @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void testFindAddressByName() {
        // Given
        // WireMock konfigurációja
        String resource = "/api/addresses";
        stubFor(get(urlPathEqualTo(resource))
                .willReturn(
                        aResponse()
                                //.withFixedDelay(10*1000)
                                .withHeader("Content-Type", "application/json")
                                .withBody("{\"city\": \"Budapest\", \"address\": \"Andrássy u. 2.\"}")));


        String url = String.format("http://%s:%d%s?name={name}", host, port, resource);
        System.out.println(url);

        AddressesGateway gateway = new AddressesGateway(url, new RestTemplateBuilder());

        // When
        AddressDto address = gateway.getAddress("John Doe");

        // Than
        verify(getRequestedFor(urlPathEqualTo(resource))
                .withQueryParam("name", equalTo("John Doe")));

        assertEquals("Budapest", address.getCity());
        assertEquals("Andrássy u. 2.", address.getAddress());
    }
}
