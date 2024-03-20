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
package io.micronaut.opensearch.restclient;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Prototype;
import io.micronaut.core.annotation.Internal;
import io.micronaut.core.util.ArrayUtils;
import io.micronaut.opensearch.conf.OpenSearchConfiguration;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestClientBuilder;

/**
 *
 * @author Sergio del Amo
 * @since 1.0.0
 */
@Internal
@Factory
final class RestClientFactory {

    /**
     * @param restClientBuilder Rest Client Builder
     * @return The OpenSearch Rest Client
     */
    @Bean(preDestroy = "close")
    RestClient restClient(RestClientBuilder restClientBuilder) {
        return restClientBuilder.build();
    }


    /**
     * @param openSearchConfiguration The {@link OpenSearchConfiguration} object
     * @return The {@link RestClientBuilder}
     */
    @Prototype
    RestClientBuilder restClientBuilder(OpenSearchConfiguration openSearchConfiguration) {
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
