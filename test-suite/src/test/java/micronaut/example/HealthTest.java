package micronaut.example;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Property(name = "endpoints.health.details-visible", value = "anonymous")
@MicronautTest
class HealthTest {

    @Test
    void healthTest(@Client("/") HttpClient httpClient) {
        BlockingHttpClient client = httpClient.toBlocking();
        String json = assertDoesNotThrow(() ->client.retrieve("/health"));
        assertTrue(json.contains("opensearch"));
    }
}
