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

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Prototype;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.annotation.Internal;
import io.micronaut.core.util.ArrayUtils;
import io.micronaut.opensearch.restclient.conf.RestClientOpenSearchConfiguration;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestClientBuilder;
import org.opensearch.client.json.jackson.JacksonJsonpMapper;
import org.opensearch.client.transport.OpenSearchTransport;
import org.opensearch.client.transport.rest_client.RestClientTransport;

/**
 * {@link Factory} to create {@link RestClient}, {@link RestClientBuilder}, {@link RequestConfig.Builder}, and {@link HttpAsyncClientBuilder} beans.
 * @author Sergio del Amo
 * @since 1.0.0
 */
@Internal
@Factory
final class RestClientFactory {

    /**
     * @param restClient Rest Client.
     * @param objectMapper Jackson's {@link ObjectMapper}
     * @return The {@link OpenSearchTransport}.
     * @since 4.2.0
     */
    @Requires(beans = { ObjectMapper.class, RestClient.class })
    @Bean(preDestroy = "close")
    @Prototype
    OpenSearchTransport openSearchTransport(RestClient restClient, ObjectMapper objectMapper) {
        return new RestClientTransport(restClient, new JacksonJsonpMapper(objectMapper));
    }

    /**
     * @param restClientBuilder Rest Client Builder
     * @return The OpenSearch Rest Client
     */
    @Bean(preDestroy = "close")
    @Prototype
    RestClient restClient(RestClientBuilder restClientBuilder) {
        return restClientBuilder.build();
    }


    /**
     * @param conf The {@link RestClientOpenSearchConfiguration} object
     * @return The {@link RestClientBuilder}
     */
    @Prototype
    RestClientBuilder restClientBuilder(RestClientOpenSearchConfiguration conf,
                                        HttpAsyncClientBuilder httpAsyncClientBuilder,
                                        RequestConfig.Builder requestConfigBuilder) {
        RestClientBuilder builder = RestClient.builder(conf.getHttpHosts())
                .setRequestConfigCallback(requestConfigB -> {
                    requestConfigB = requestConfigBuilder;
                    return requestConfigB;
                }).setHttpClientConfigCallback(httpClientBuilder -> {
                    httpClientBuilder = httpAsyncClientBuilder;
                    return httpClientBuilder;
                });
        if (ArrayUtils.isNotEmpty(conf.getDefaultHeaders())) {
            builder.setDefaultHeaders(conf.getDefaultHeaders());
        }
        if (conf.getNodeSelector() != null) {
            builder.setNodeSelector(conf.getNodeSelector());
        }
        return builder;
    }

    @Prototype
    RequestConfig.Builder createRequestConfigBuilder() {
        return RequestConfig.custom();
    }

    /**
     * @return The {@link HttpAsyncClientBuilder} bean.
     */
    @Prototype
    HttpAsyncClientBuilder httpAsyncClientBuilder() {
        return HttpAsyncClientBuilder.create();
    }
}
