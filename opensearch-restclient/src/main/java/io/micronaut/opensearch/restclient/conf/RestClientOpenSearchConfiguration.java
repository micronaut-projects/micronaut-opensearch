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

import io.micronaut.core.util.Toggleable;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.opensearch.client.NodeSelector;

/**
 * Generic interface for the OpenSearch Configuration beans.
 */
public interface RestClientOpenSearchConfiguration extends Toggleable {

    /**
     * The one or more hosts that the client will communicate with, provided as
     * instances of {@link HttpHost}.
     *
     * @return An array of {@link HttpHost}
     */
    HttpHost[] getHttpHosts();

    /**
     * The default headers that need to be sent with each request, to prevent having
     * to specify them with each single request.
     *
     * @return An array of {@link Header}.
     */
    Header[] getDefaultHeaders();

    /**
     * The node selector to be used to filter the nodes the client will send
     * requests to among the ones that are set to the client itself. By default, the
     * client sends requests to every configured node.
     *
     * @return The {@link NodeSelector} to be used.
     */
    NodeSelector getNodeSelector();

}
