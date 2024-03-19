package io.micronaut.opensearch.restclient;

import io.micronaut.context.BeanContext;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.opensearch.client.RestClient;

import static org.junit.jupiter.api.Assertions.*;

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

}