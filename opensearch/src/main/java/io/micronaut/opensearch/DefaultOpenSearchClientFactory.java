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

import io.micronaut.context.annotation.Factory;
import io.micronaut.core.annotation.Internal;
import jakarta.inject.Singleton;
import org.opensearch.client.opensearch.OpenSearchAsyncClient;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.transport.OpenSearchTransport;

/**
 * Factory responsible for creating OpenSearch clients.
 */
@Factory
@Internal
final class DefaultOpenSearchClientFactory {
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
}
