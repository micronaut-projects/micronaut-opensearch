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
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.annotation.Internal;
import org.opensearch.client.RestClient;
import org.opensearch.client.json.jackson.JacksonJsonpMapper;
import org.opensearch.client.transport.OpenSearchTransport;
import org.opensearch.client.transport.rest_client.RestClientTransport;

/**
 * Factory for creating an {@link OpenSearchTransport} with Jackson's {@link ObjectMapper}.
 * @author Sergio del Amo
 * @since 1.0.0
 */
@Internal
@Factory
@Requires(beans = ObjectMapper.class)
final class OpenSearchTransportFactory {

    /**
     * @param restClient Rest Client.
     * @param objectMapper Jackson's {@link ObjectMapper}
     * @return The {@link OpenSearchTransport}.
     * @since 4.2.0
     */
    @Bean(preDestroy = "close")
    OpenSearchTransport openSearchTransport(RestClient restClient,
                                            ObjectMapper objectMapper) {
        return new RestClientTransport(restClient, new JacksonJsonpMapper(objectMapper));
    }

}
