/*
 * Copyright 2017-2024 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.opensearch.httpclient5.conf;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.annotation.Internal;
import io.micronaut.opensearch.conf.OpenSearchConfigurationProperties;
import org.apache.hc.core5.http.HttpHost;

/**
 * {@link ConfigurationProperties} implementation of {@link HttpClient5OpenSearchConfiguration}.
 * @since 1.0.0
 */
@ConfigurationProperties(HttpClient5OpenSearchConfigurationProperties.PREFIX)
@Internal
public final class HttpClient5OpenSearchConfigurationProperties implements HttpClient5OpenSearchConfiguration {
    /**
     * The default enable value.
     */
    @SuppressWarnings("WeakerAccess")
    public static final boolean DEFAULT_ENABLED = true;

    /**
     * OpenSearch configuration.
     */
    public static final String PREFIX = OpenSearchConfigurationProperties.PREFIX + ".httpclient5";

    private HttpHost[] httpHosts;
    private boolean enabled = DEFAULT_ENABLED;

    @Override
    public HttpHost[] getHttpHosts() {
        return this.httpHosts;
    }

    /**
     * @param httpHosts One or more hosts that client will connect to.
     */
    public void setHttpHosts(HttpHost[] httpHosts) {
        this.httpHosts = httpHosts;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * If OpenSearch integration is enabled. Default value {@value #DEFAULT_ENABLED}
     *
     * @param enabled True if security is enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
