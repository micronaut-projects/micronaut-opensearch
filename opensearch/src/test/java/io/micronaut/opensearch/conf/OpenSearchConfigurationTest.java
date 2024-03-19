package io.micronaut.opensearch.conf;

import io.micronaut.context.ApplicationContext;
import io.micronaut.context.BeanContext;
import io.micronaut.core.util.StringUtils;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.apache.http.HttpHost;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false)
class OpenSearchConfigurationTest {

    @Inject
    BeanContext beanContext;

    @Test
    void beanOfTypeOpenSearchConfigurationExistsByDefault() {
        assertTrue(beanContext.containsBean(OpenSearchConfiguration.class));
    }

    @Test
    void httpHostsDefaultsToHttp1270019200() {
        OpenSearchConfiguration openSearchConfiguration = beanContext.getBean(OpenSearchConfiguration.class);
        assertEquals(1, openSearchConfiguration.getHttpHosts().length);
        HttpHost httpHost = openSearchConfiguration.getHttpHosts()[0];
        assertEquals("127.0.0.1", httpHost.getHostName());
        assertEquals(9200, httpHost.getPort());
        assertEquals("http", httpHost.getSchemeName());
    }
}