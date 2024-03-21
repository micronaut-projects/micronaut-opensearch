package io.micronaut.opensearch.aws;

import io.micronaut.context.BeanContext;
import io.micronaut.context.annotation.Property;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
@Property(name = "micronaut.opensearch.aws.endpoint", value = "search-micronautguide-2abc3a4ab4s2cabc2r2vmbja.aos.us-east-1.on.aws")
@Property(name = "micronaut.opensearch.aws.signing-region", value = "us-east-1")
@MicronautTest(startApplication = false)
class AmazonOpenSearchConfigurationTest {

    @Inject
    BeanContext beanContext;

    @Test
    void amazonOpenSearchConfigurationBeanExists() {
        assertTrue(beanContext.containsBean(AmazonOpenSearchConfiguration.class));
        AmazonOpenSearchConfiguration amazonOpenSearchConfiguration = beanContext.getBean(AmazonOpenSearchConfiguration.class);
        assertNotNull(amazonOpenSearchConfiguration.getEndpoint());
        assertNotNull(amazonOpenSearchConfiguration.getSigningRegion());
    }
}