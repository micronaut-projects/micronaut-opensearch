package io.micronaut.opensearch.restclient;

import io.micronaut.context.BeanContext;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.junit.jupiter.api.Test;
import org.opensearch.client.RestClient;
import org.opensearch.client.transport.OpenSearchTransport;

import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest(startApplication = false)
class RestClientFactoryTest {
    @Inject
    BeanContext beanContext;

    @Test
    void beanOfTypeRestClientExists() {
        assertTrue(beanContext.containsBean(RestClient.class));
    }

    @Test
    void beanOfTypeRestClientBuilderExists() {
        assertTrue(beanContext.containsBean(RestClient.class));
    }

    @Test
    void beanOfTypeHttpAsyncClientBuilderExists() {
        assertTrue(beanContext.containsBean(HttpAsyncClientBuilder.class));
    }

    @Test
    void beanOfTypeOpenSearchTransportExists() {
        assertTrue(beanContext.containsBean(OpenSearchTransport.class));
    }
}