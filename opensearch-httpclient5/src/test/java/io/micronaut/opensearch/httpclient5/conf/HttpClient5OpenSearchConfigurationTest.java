package io.micronaut.opensearch.httpclient5.conf;

import io.micronaut.context.ApplicationContext;
import io.micronaut.context.BeanContext;
import io.micronaut.context.annotation.Property;
import io.micronaut.core.util.StringUtils;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.apache.hc.core5.http.HttpHost;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@Property(name = "micronaut.opensearch.httpclient5.default-headers[0]", value = "Foo:Bar")
@Property(name = "micronaut.opensearch.httpclient5.http-hosts[0]", value = "http://127.0.0.1:9200")
@MicronautTest(startApplication = false)
class HttpClient5OpenSearchConfigurationTest {

    @Inject
    BeanContext beanContext;

    @Test
    void beanOfTypeOpenSearchConfigurationExistsByDefault() {
        assertTrue(beanContext.containsBean(HttpClient5OpenSearchConfiguration.class));
    }

    @Test
    void beanOfTypeHttpClient5OpenSearchConfigurationCanBeDisabled() {
        try (ApplicationContext ctx = ApplicationContext.run(Collections.singletonMap("micronaut.opensearch.enabled", StringUtils.FALSE))) {
            assertFalse(ctx.containsBean(HttpClient5OpenSearchConfiguration.class));
        }
    }
    @Test
    void httpHostsDefaultsToHttp1270019200() {
        HttpClient5OpenSearchConfiguration openSearchConfiguration = beanContext.getBean(HttpClient5OpenSearchConfiguration.class);
        assertEquals(1, openSearchConfiguration.getHttpHosts().length);
        HttpHost httpHost = openSearchConfiguration.getHttpHosts()[0];
        assertEquals("127.0.0.1", httpHost.getHostName());
        assertEquals(9200, httpHost.getPort());
        assertEquals("http", httpHost.getSchemeName());
    }
}