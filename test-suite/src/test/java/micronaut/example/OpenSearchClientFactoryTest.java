package micronaut.example;

import io.micronaut.context.BeanContext;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.opensearch.client.opensearch.OpenSearchAsyncClient;
import org.opensearch.client.opensearch.OpenSearchClient;

import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest(startApplication = false)
class OpenSearchClientFactoryTest {
    @Inject
    BeanContext beanContext;

    @Test
    void beanOfTypeOpenSearchClientExists() {
        assertTrue(beanContext.containsBean(OpenSearchClient.class));
    }

    @Test
    void beanOfTypeOpenSearchAsyncClientExists() {
        assertTrue(beanContext.containsBean(OpenSearchAsyncClient.class));
    }
}