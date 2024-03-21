package io.micronaut.opensearch.restclient.conf;

import io.micronaut.context.ApplicationContext;
import io.micronaut.context.BeanContext;
import io.micronaut.context.annotation.Property;
import io.micronaut.core.util.StringUtils;
import io.micronaut.opensearch.restclient.conf.RestClientOpenSearchConfiguration;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@Property(name = "micronaut.opensearch.rest-client.default-headers[0]", value = "Foo:Bar")
@Property(name = "micronaut.opensearch.rest-client.http-hosts[0]", value = "http://127.0.0.1:9200")
@MicronautTest(startApplication = false)
class RestClientOpenSearchConfigurationTest {

    @Inject
    BeanContext beanContext;

    @Test
    void beanOfTypeOpenSearchConfigurationExistsByDefault() {
        assertTrue(beanContext.containsBean(RestClientOpenSearchConfiguration.class));
    }

    @Test
    void beanOfTypeRestClientOpenSearchConfigurationCanBeDisabled() {
        try (ApplicationContext ctx = ApplicationContext.run(Collections.singletonMap("micronaut.opensearch.rest-client.enabled", StringUtils.FALSE))) {
            assertFalse(ctx.containsBean(RestClientOpenSearchConfiguration.class));
        }
        try (ApplicationContext ctx = ApplicationContext.run(Collections.singletonMap("micronaut.opensearch.enabled", StringUtils.FALSE))) {
            assertFalse(ctx.containsBean(RestClientOpenSearchConfiguration.class));
        }
    }

    @Test
    void defaultHttpHeadersCanBeSetViaConfiguration() {
        RestClientOpenSearchConfiguration openSearchConfiguration = beanContext.getBean(RestClientOpenSearchConfiguration.class);
        assertEquals(1, openSearchConfiguration.getDefaultHeaders().length);
        Header header = openSearchConfiguration.getDefaultHeaders()[0];
        assertEquals("Foo", header.getName());
        assertEquals("Bar", header.getValue());
    }

    @Test
    void httpHostsDefaultsToHttp1270019200() {
        RestClientOpenSearchConfiguration openSearchConfiguration = beanContext.getBean(RestClientOpenSearchConfiguration.class);
        assertEquals(1, openSearchConfiguration.getHttpHosts().length);
        HttpHost httpHost = openSearchConfiguration.getHttpHosts()[0];
        assertEquals("127.0.0.1", httpHost.getHostName());
        assertEquals(9200, httpHost.getPort());
        assertEquals("http", httpHost.getSchemeName());
    }
}