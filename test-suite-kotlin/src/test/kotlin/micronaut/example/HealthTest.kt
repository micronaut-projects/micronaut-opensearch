package micronaut.example

import io.micronaut.context.annotation.Property
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

@MicronautTest
@Property(name = "endpoints.health.details-visible", value = "anonymous")
@Property(name = "micronaut.http.client.read-timeout", value = "120s")
class HealthTest {

    @Test
    fun healthTest(@Client("/") httpClient: HttpClient) {
        val client = httpClient.toBlocking()
        val json = assertDoesNotThrow<String> {
            client.retrieve(
                "/health"
            )
        }
        assertTrue(json.contains("opensearch"))
    }
}
