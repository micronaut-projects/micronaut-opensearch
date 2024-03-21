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

import io.micronaut.core.util.Toggleable;
import org.apache.hc.core5.http.HttpHost;

/**
 * Generic interface for the OpenSearch Configuration beans.
 */
public interface HttpClient5OpenSearchConfiguration extends Toggleable {

    /**
     * The one or more hosts that the client will communicate with, provided as
     * instances of {@link HttpHost}.
     *
     * @return An array of {@link HttpHost}
     */
    HttpHost[] getHttpHosts();
}
