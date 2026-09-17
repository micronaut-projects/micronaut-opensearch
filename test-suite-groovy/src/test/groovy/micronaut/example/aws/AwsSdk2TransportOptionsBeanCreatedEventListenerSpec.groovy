package micronaut.example.aws

import io.micronaut.context.annotation.Property
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import org.opensearch.client.transport.OpenSearchTransport
import org.opensearch.client.transport.aws.AwsSdk2Transport
import spock.lang.Specification

@Property(name = "micronaut.opensearch.aws.enabled", value = "true")
@Property(name = "micronaut.opensearch.aws.endpoint", value = "search-micronautguide-2abc3a4ab4s2cabc2r2vmbja.aos.us-east-1.on.aws")
@Property(name = "micronaut.opensearch.aws.signing-region", value = "us-east-1")
@Property(name = "micronaut.opensearch.rest-client.enabled", value = "false")
@MicronautTest(startApplication = false)
class AwsSdk2TransportOptionsBeanCreatedEventListenerSpec extends Specification {

    @Inject
    OpenSearchTransport transport

    @Inject
    AwsSdk2TransportOptionsBeanCreatedEventListener listener

    void "listener customizes the transport options"() {
        expect:
        transport instanceof AwsSdk2Transport
        listener.invoked
    }
}
