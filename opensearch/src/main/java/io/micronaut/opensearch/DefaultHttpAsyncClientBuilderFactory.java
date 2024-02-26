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

import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.opensearch.client.RestClient;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.annotation.Order;
import io.micronaut.core.order.Ordered;
import jakarta.inject.Singleton;

/**
 * Factory for building the Apache HTTP Client used as the transport base for the official OpenSearch clients.
 */
@Requires(classes = { RestClient.class })
@Factory
public class DefaultHttpAsyncClientBuilderFactory {

    /**
     * The http client configuration (e.g. encrypted communication over ssl, or
     * anything that the {@link HttpAsyncClientBuilder} allows to set).
     *
     * @return The {@link HttpAsyncClientBuilder} bean with default configurations.
     */
    @Bean
    @Singleton
    @Order(Ordered.LOWEST_PRECEDENCE) // Allow to co-exist with the Elastic search version.
    protected HttpAsyncClientBuilder httpAsyncClientBuilder() {
        return HttpAsyncClientBuilder.create();
    }
}
