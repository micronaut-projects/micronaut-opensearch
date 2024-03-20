package micronaut.example

import io.micronaut.context.annotation.Property
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@Property(name = "endpoints.health.details-visible", value = "anonymous")
@MicronautTest
class HealthSpec extends Specification {

    @Inject
    @Client("/")
    HttpClient httpClient

    void healthTest() {
        given:
        BlockingHttpClient client = httpClient.toBlocking()

        when:
        String json = client.retrieve("/health")

        then:
        noExceptionThrown()
        json.contains("opensearch")
    }
}
