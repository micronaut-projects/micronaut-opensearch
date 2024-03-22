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
package io.micronaut.opensearch.restclient.conf;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.annotation.Internal;
import io.micronaut.opensearch.conf.OpenSearchConfigurationProperties;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.opensearch.client.NodeSelector;

/**
 * {@link ConfigurationProperties} implementation of {@link RestClientOpenSearchConfiguration}.
 * @since 1.0.0
 */
@ConfigurationProperties(RestClientOpenSearchConfigurationProperties.PREFIX)
@Internal
public final class RestClientOpenSearchConfigurationProperties implements RestClientOpenSearchConfiguration {
    /**
     * The default enable value.
     */
    @SuppressWarnings("WeakerAccess")
    public static final boolean DEFAULT_ENABLED = true;

    /**
     * OpenSearch configuration.
     */
    public static final String PREFIX = OpenSearchConfigurationProperties.PREFIX + ".rest-client";

    private NodeSelector nodeSelector;
    private HttpHost[] httpHosts;
    private Header[] defaultHeaders;

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

    /**
     * @param nodeSelector The {@link NodeSelector} to be used, in case of multiple
     *                     nodes.
     */
    public void setNodeSelector(NodeSelector nodeSelector) {
        this.nodeSelector = nodeSelector;
    }

    @Override
    public NodeSelector getNodeSelector() {
        return this.nodeSelector;
    }

    @Override
    public Header[] getDefaultHeaders() {
        return this.defaultHeaders;
    }

    /**
     * @param defaultHeaders The defaults {@link Header} to sent with each request.
     */
    public void setDefaultHeaders(Header[] defaultHeaders) {
        this.defaultHeaders = defaultHeaders;
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
