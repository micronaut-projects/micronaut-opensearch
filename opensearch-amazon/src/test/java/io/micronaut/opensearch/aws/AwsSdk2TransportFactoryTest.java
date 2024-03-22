package io.micronaut.opensearch.aws;

import io.micronaut.context.BeanContext;
import io.micronaut.context.annotation.Property;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.opensearch.client.transport.OpenSearchTransport;
import org.opensearch.client.transport.aws.AwsSdk2Transport;
import org.opensearch.client.transport.aws.AwsSdk2TransportOptions;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Property(name = "micronaut.opensearch.aws.endpoint", value = "search-micronautguide-2abc3a4ab4s2cabc2r2vmbja.aos.us-east-1.on.aws")
@Property(name = "micronaut.opensearch.aws.signing-region", value = "us-east-1")
@MicronautTest(startApplication = false)
class AwsSdk2TransportFactoryTest {

    @Inject
    BeanContext beanContext;

    @Test
    void beanOfTypeAwsSdk2TransportFactory() {
        assertTrue(beanContext.containsBean(OpenSearchTransport.class));
        OpenSearchTransport openSearchTransport = assertDoesNotThrow(() -> beanContext.getBean(OpenSearchTransport.class));
        assertTrue(openSearchTransport instanceof AwsSdk2Transport);
    }

    @Test
    void awsSdk2TransportOptionsBuilderExists(AwsSdk2TransportOptionsBeanCreatedEventListener awsSdk2TransportOptionsBeanCreatedEventListener) {
        assertTrue(beanContext.containsBean(AwsSdk2TransportOptions.Builder.class));
        assertTrue(awsSdk2TransportOptionsBeanCreatedEventListener.isInvoked());
    }

}
