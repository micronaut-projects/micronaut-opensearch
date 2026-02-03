package micronaut.example

import io.micronaut.context.annotation.Property
import io.micronaut.core.annotation.NonNull
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.opensearch.testresources.OpenSearch
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.micronaut.test.support.TestPropertyProvider
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@MicronautTest
@Property(name = "endpoints.health.details-visible", value = "anonymous")
@Property(name = "micronaut.http.client.read-timeout", value = "120s")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HealthTest : TestPropertyProvider {

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

    override fun getProperties(): Map<String, String> = OpenSearch.getProperties()
}
