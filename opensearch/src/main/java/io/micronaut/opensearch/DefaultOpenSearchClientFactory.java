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
package io.micronaut.opensearch;

import io.micronaut.opensearch.conf.OpenSearchConfiguration;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestClientBuilder;
import org.opensearch.client.json.jackson.JacksonJsonpMapper;
import org.opensearch.client.opensearch.OpenSearchAsyncClient;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.transport.OpenSearchTransport;
import org.opensearch.client.transport.rest_client.RestClientTransport;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.util.ArrayUtils;
import jakarta.inject.Singleton;

/**
 * Factory responsible for creating OpenSearch clients.
 */
@Requires(beans = OpenSearchConfiguration.class)
@Factory
public class DefaultOpenSearchClientFactory {

    /**
     * @param openSearchConfiguration OpenSearch Configuration.
     * @return The OpenSearch Rest Client
     */
    @Bean(preDestroy = "close")
    RestClient restClient(OpenSearchConfiguration openSearchConfiguration) {
        return restClientBuilder(openSearchConfiguration).build();
    }

    /**
     * @param transport The {@link OpenSearchTransport} object.
     * @return The OpenSearchClient.
     * @since 4.2.0
     */
    @Singleton
    OpenSearchClient openSearchClient(OpenSearchTransport transport) {
        return new OpenSearchClient(transport);
    }

    /**
     * @param transport The {@link OpenSearchTransport} object.
     * @return The OpenSearchAsyncClient.
     * @since 4.2.0
     */
    @Singleton
    OpenSearchAsyncClient openSearchAsyncClient(OpenSearchTransport transport) {
        return new OpenSearchAsyncClient(transport);
    }

    /**
     * @param openSearchConfiguration The
     *                                {@link OpenSearchConfiguration}
     *                                object.
     * @param objectMapper            The {@link ObjectMapper} object.
     * @return The {@link OpenSearchTransport}.
     * @since 4.2.0
     */
    @Singleton
    @Bean(preDestroy = "close")
    OpenSearchTransport openSearchTransport(OpenSearchConfiguration openSearchConfiguration,
                                            ObjectMapper objectMapper) {
        RestClient restClient = restClientBuilder(openSearchConfiguration).build();

        OpenSearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper(objectMapper));
        return transport;
    }

    /**
     * @param openSearchConfiguration The
     *                                {@link OpenSearchConfiguration}
     *                                object
     * @return The {@link RestClientBuilder}
     */
    protected RestClientBuilder restClientBuilder(OpenSearchConfiguration openSearchConfiguration) {
        RestClientBuilder builder = RestClient.builder(openSearchConfiguration.getHttpHosts())
            .setRequestConfigCallback(requestConfigBuilder -> {
                requestConfigBuilder = openSearchConfiguration.getRequestConfigBuilder();
                return requestConfigBuilder;
            }).setHttpClientConfigCallback(httpClientBuilder -> {
                httpClientBuilder = openSearchConfiguration.getHttpAsyncClientBuilder();
                return httpClientBuilder;
            });

        if (ArrayUtils.isNotEmpty(openSearchConfiguration.getDefaultHeaders())) {
            builder.setDefaultHeaders(openSearchConfiguration.getDefaultHeaders());
        }

        return builder;
    }

}
