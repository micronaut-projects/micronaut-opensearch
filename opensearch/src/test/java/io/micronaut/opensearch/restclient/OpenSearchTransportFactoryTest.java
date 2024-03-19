package io.micronaut.opensearch.restclient;

import io.micronaut.context.BeanContext;
import io.micronaut.opensearch.conf.OpenSearchConfiguration;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.opensearch.client.transport.OpenSearchTransport;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false)
class OpenSearchTransportFactoryTest {
    @Inject
    BeanContext beanContext;

    @Test
    void beanOfTypeOpenSearchTransportExists() {
        assertTrue(beanContext.containsBean(OpenSearchTransport.class));
    }
}