package micronaut.example

import io.micronaut.context.annotation.Property
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.opensearch.testresources.OpenSearch
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import io.micronaut.test.support.TestPropertyProvider
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
@Property(name = "endpoints.health.details-visible", value = "anonymous")
@Property(name = "micronaut.http.client.read-timeout", value = "120s")
class HealthSpec extends Specification implements TestPropertyProvider{

    @Override
    Map<String, String> getProperties() {
        return OpenSearch.properties
    }

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
