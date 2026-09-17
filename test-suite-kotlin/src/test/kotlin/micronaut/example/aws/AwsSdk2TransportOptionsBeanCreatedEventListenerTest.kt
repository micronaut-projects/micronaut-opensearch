package micronaut.example.aws

import io.micronaut.context.annotation.Property
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.opensearch.client.transport.OpenSearchTransport
import org.opensearch.client.transport.aws.AwsSdk2Transport

@Property(name = "micronaut.opensearch.aws.enabled", value = "true")
@Property(name = "micronaut.opensearch.aws.endpoint", value = "search-micronautguide-2abc3a4ab4s2cabc2r2vmbja.aos.us-east-1.on.aws")
@Property(name = "micronaut.opensearch.aws.signing-region", value = "us-east-1")
@Property(name = "micronaut.opensearch.rest-client.enabled", value = "false")
@MicronautTest(startApplication = false)
class AwsSdk2TransportOptionsBeanCreatedEventListenerTest {

    @Inject
    lateinit var transport: OpenSearchTransport

    @Inject
    lateinit var listener: AwsSdk2TransportOptionsBeanCreatedEventListener

    @Test
    fun listenerCustomizesTheTransportOptions() {
        assertInstanceOf(AwsSdk2Transport::class.java, transport)
        assertTrue(listener.invoked)
    }
}
