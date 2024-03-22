package io.micronaut.opensearch.aws;

import io.micronaut.context.BeanContext;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.http.SdkHttpClient;

import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest(startApplication = false)
class SdkHttpClientTest {
    @Inject
    BeanContext beanContext;

    @Test
    void beanOfTypeSdkHttpClientExists() {
        assertTrue(beanContext.containsBean(SdkHttpClient.class));
    }
}
