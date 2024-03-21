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
package io.micronaut.opensearch.aws;

import io.micronaut.core.util.Toggleable;
import jakarta.annotation.Nonnull;
import software.amazon.awssdk.regions.Region;

/**
 * Configuration for Amazon OpenSearch Service.
 * @author Sergio del Amo
 * @since 1.0.0
 */
public interface AmazonOpenSearchConfiguration extends Toggleable {
    /**
     *
     * @return OpenSearch endpoint, without https:// For example: `search-...us-east-1.aoss.amazonaws.com`.
     */
    @Nonnull
    String getEndpoint();

    /**
     *
     * @return The region to use for signing requests.
     */
    @Nonnull
    Region getSigningRegion();
}
