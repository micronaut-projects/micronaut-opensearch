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
package io.micronaut.opensearch.convert;

import java.util.Locale;
import java.util.Optional;

import org.opensearch.client.NodeSelector;
import org.opensearch.client.RestClientBuilder;

import io.micronaut.context.annotation.Requires;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;
import jakarta.inject.Singleton;

/**
 * Converter for CharSequence to NodeSelector.
 */
@Singleton
@Requires(classes = RestClientBuilder.class)
public class StringToNodeSelectorConverter implements TypeConverter<CharSequence, NodeSelector> {

    @Override
    public Optional<NodeSelector> convert(CharSequence object, Class<NodeSelector> targetType,
            ConversionContext context) {
        String nodeSelector = object.toString().toUpperCase(Locale.ENGLISH);
        switch (nodeSelector) {
        case "SKIP_DEDICATED_CLUSTER_MANAGERS":
        case "SKIP_DEDICATED_MASTERS":
            return Optional.of(NodeSelector.SKIP_DEDICATED_CLUSTER_MANAGERS);
        case "ANY":
            return Optional.of(NodeSelector.ANY);
        default:
            return Optional.empty();
        }
    }
}