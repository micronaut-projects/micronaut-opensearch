package io.micronaut.opensearch.conf;

import io.micronaut.context.ApplicationContext;
import io.micronaut.context.BeanContext;
import io.micronaut.core.util.StringUtils;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest(startApplication = false)
class OpenSearchConfigurationTest {

    @Inject
    BeanContext beanContext;

    @Test
    void beanOfTypeOpenSearchConfigurationExistsByDefault() {
        assertTrue(beanContext.containsBean(OpenSearchConfiguration.class));
    }
}