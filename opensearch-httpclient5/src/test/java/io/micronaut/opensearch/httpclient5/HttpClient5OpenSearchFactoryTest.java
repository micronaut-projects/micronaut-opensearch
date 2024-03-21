package io.micronaut.opensearch.httpclient5;

import io.micronaut.context.BeanContext;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

@MicronautTest(startApplication = false)
class HttpClient5OpenSearchFactoryTest {

    @Inject
    BeanContext beanContext;
}