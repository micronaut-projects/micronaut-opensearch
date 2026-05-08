package io.micronaut.opensearch.testresources;

import org.opensearch.testcontainers.OpenSearchContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

/**
 * A test resource provider which will spawn an OpenSearch test container.
 */
public class OpenSearch {
    private static final String IMAGE_NAME = "opensearchproject/opensearch:latest";
    private static OpenSearchContainer<?> container;

    public static Map<String, String> getProperties() {
        if (container == null) {
            container = new OpenSearchContainer<>(DockerImageName.parse(IMAGE_NAME));
            container.start();
            do {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } while (!container.isRunning());
            return getProperties(container);
        } else {
            return getProperties(container);
        }
    }

    private static Map<String, String> getProperties(OpenSearchContainer<?> container) {
        return Map.of(
            "micronaut.opensearch.rest-client.http-hosts", container.getHttpHostAddress(),
            "micronaut.opensearch.httpclient5.http-hosts", container.getHttpHostAddress()
        );
    }
}
