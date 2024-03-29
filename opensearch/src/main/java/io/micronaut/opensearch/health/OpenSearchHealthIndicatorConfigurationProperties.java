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
package io.micronaut.opensearch.health;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.management.endpoint.health.HealthEndpoint;

@ConfigurationProperties(OpenSearchHealthIndicatorConfigurationProperties.PREFIX)
final class OpenSearchHealthIndicatorConfigurationProperties implements OpenSearchHealthIndicatorConfiguration {
    /**
     * The default enable value.
     */
    @SuppressWarnings("WeakerAccess")
    public static final boolean DEFAULT_ENABLED = true;

    public static final String PREFIX = HealthEndpoint.PREFIX + ".opensearch";

    private boolean enabled = DEFAULT_ENABLED;

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Whether OpenSearch Health Indicator is enabled. Default value {@value #DEFAULT_ENABLED}
     *
     * @param enabled True if security is enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
