package io.micronaut.opensearch;

import io.micronaut.testresources.testcontainers.AbstractTestContainersProvider;
import org.opensearch.testcontainers.OpensearchContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A test resource provider which will spawn an OpenSearch test container.
 */
public class OpenSearchTestResourceProvider extends AbstractTestContainersProvider<OpensearchContainer<?>> {
    public static final String OPENSEARCH_HOSTS = "micronaut.opensearch.rest-client.http-hosts";
    public static final String SIMPLE_NAME = "opensearch";
    public static final String DEFAULT_IMAGE = "opensearchproject/opensearch";
    public static final String DISPLAY_NAME = "OpenSearch";

    @Override
    public List<String> getResolvableProperties(Map<String, Collection<String>> propertyEntries, Map<String, Object> testResourcesConfig) {
        return Collections.singletonList(OPENSEARCH_HOSTS);
    }

    @Override
    public String getDisplayName() {
        return DISPLAY_NAME;
    }

    @Override
    protected String getSimpleName() {
        return SIMPLE_NAME;
    }

    @Override
    protected String getDefaultImageName() {
        return DEFAULT_IMAGE;
    }

    @Override
    @SuppressWarnings("resource") // The container is long-lived and closed elsewhere
    protected OpensearchContainer<?> createContainer(DockerImageName imageName, Map<String, Object> requestedProperties, Map<String, Object> testResourcesConfig) {
        return new OpensearchContainer<>(imageName)
            .withAccessToHost(true);
    }

    @Override
    protected Optional<String> resolveProperty(String propertyName, OpensearchContainer container) {
        if (OPENSEARCH_HOSTS.equals(propertyName)) {
            return Optional.of(container.getHttpHostAddress());
        }
        return Optional.empty();
    }

    @Override
    protected boolean shouldAnswer(String propertyName, Map<String, Object> requestedProperties, Map<String, Object> testResourcesConfig) {
        return OPENSEARCH_HOSTS.equals(propertyName);
    }
}
