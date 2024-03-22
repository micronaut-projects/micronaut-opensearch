package io.micronaut.opensearch;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.opensearch.testresources.OpenSearchTestResourceProvider;

/**
 * Spawn an OpenSearch test container for httpclient5 tests.
 */
public class Http5ClientOpenSearchTestResourceProvider extends OpenSearchTestResourceProvider {

    @Override
    @NonNull
    protected String getProperty() {
        return "micronaut.opensearch.httpclient5.http-hosts";
    }
}
