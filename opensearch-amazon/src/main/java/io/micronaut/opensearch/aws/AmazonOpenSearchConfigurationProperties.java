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

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.opensearch.conf.OpenSearchConfigurationProperties;
import jakarta.annotation.Nonnull;
import software.amazon.awssdk.regions.Region;

/**
 * {@link ConfigurationProperties} implementation of {@link AmazonOpenSearchConfiguration}.
 * @author Sergio del Amo
 * @since 1.0.0
 */
@ConfigurationProperties(AmazonOpenSearchConfigurationProperties.PREFIX)
public class AmazonOpenSearchConfigurationProperties implements AmazonOpenSearchConfiguration {
    /**
     * The default enable value.
     */
    @SuppressWarnings("WeakerAccess")
    public static final boolean DEFAULT_ENABLED = true;
    /**
     * The prefix to use for Amazon OpenSearch settings.
     */
    public static final String PREFIX = OpenSearchConfigurationProperties.PREFIX + ".aws";

    private String endpoint;
    private Region signingRegion;
    private boolean enabled = DEFAULT_ENABLED;

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Whether Amazon OpenSearch Service integration is enabled. Default value {@value #DEFAULT_ENABLED}
     *
     * @param enabled True if security is enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Nonnull
    @Override
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * Sets the OpenSearch endpoint, without https:// For example: `search-...us-east-1.aoss.amazonaws.com`.
     * @param endpoint OpenSearch endpoint, without https:// For example: `search-...us-east-1.aoss.amazonaws.com`.
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Nonnull
    @Override
    public Region getSigningRegion() {
        return signingRegion;
    }

    /**
     * The region to use for signing requests. For example: `us-east-1`.
     * @param signingRegion The region to use for signing requests. For example: {@link Region#US_EAST_1}.
     */
    public void setSigningRegion(Region signingRegion) {
        this.signingRegion = signingRegion;
    }
}
