package micronaut.example.support;

import io.micronaut.context.ApplicationContext;
import io.micronaut.context.ApplicationContextConfigurer;
import io.micronaut.context.annotation.ContextConfigurer;
import io.micronaut.context.env.Environment;
import io.micronaut.context.env.PropertySource;
import io.micronaut.opensearch.testresources.OpenSearch;

import java.util.Map;

/**
 * Supplies the address of the OpenSearch test container to the tests run with the {@value #OPENSEARCH_ENVIRONMENT}
 * environment, like the {@code TestPropertyProvider} of the Java, Kotlin and Groovy tests does.
 * <p>
 * The configurer is written in Java because Micronaut Test calls {@code TestPropertyProvider} before the application
 * context, and with it the GraalPy runtime, exists, so a Python test class cannot supply the container properties. It
 * uses the {@link #configure(ApplicationContext)} callback because the {@link io.micronaut.context.ApplicationContextBuilder}
 * is configured before {@code @MicronautTest} selects the environments, so the environment can only be checked on the
 * built context.
 */
@ContextConfigurer
public class OpenSearchTestConfigurer implements ApplicationContextConfigurer {

    public static final String OPENSEARCH_ENVIRONMENT = "opensearch";

    @Override
    public void configure(ApplicationContext applicationContext) {
        Environment environment = applicationContext.getEnvironment();
        if (environment.getActiveNames().contains(OPENSEARCH_ENVIRONMENT)) {
            environment.addPropertySource(PropertySource.of(OPENSEARCH_ENVIRONMENT, Map.copyOf(OpenSearch.getProperties())));
        }
    }
}
