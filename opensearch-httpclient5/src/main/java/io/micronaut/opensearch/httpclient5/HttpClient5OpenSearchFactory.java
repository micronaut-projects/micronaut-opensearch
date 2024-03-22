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
package io.micronaut.opensearch.httpclient5;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Prototype;
import io.micronaut.core.annotation.Internal;
import io.micronaut.opensearch.httpclient5.conf.HttpClient5OpenSearchConfiguration;
import org.opensearch.client.transport.OpenSearchTransport;
import org.opensearch.client.transport.httpclient5.ApacheHttpClient5Transport;
import org.opensearch.client.transport.httpclient5.ApacheHttpClient5TransportBuilder;

/**
 * {@link Factory} to create {@link ApacheHttpClient5TransportBuilder}, and {@link ApacheHttpClient5Transport} beans.
 * @author Sergio del Amo
 * @since 1.0.0
 */
@Factory
@Internal
final class HttpClient5OpenSearchFactory {

    @Prototype
    ApacheHttpClient5TransportBuilder createApacheHttpClient5TransportBuilder(HttpClient5OpenSearchConfiguration httpClient5OpenSearchConfiguration) {
        return ApacheHttpClient5TransportBuilder.builder(httpClient5OpenSearchConfiguration.getHttpHosts());
    }

    @Prototype
    OpenSearchTransport createOpenSearchTransport(ApacheHttpClient5TransportBuilder apacheHttpClient5TransportBuilder) {
        return apacheHttpClient5TransportBuilder.build();
    }
}
