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

import io.micronaut.core.annotation.Internal;
import org.opensearch.client.NodeSelector;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;

/**
 * Converter for CharSequence to NodeSelector.
 */
@Internal
final class StringToNodeSelectorConverter implements TypeConverter<CharSequence, NodeSelector> {

    @Override
    public Optional<NodeSelector> convert(CharSequence object, Class<NodeSelector> targetType, ConversionContext context) {
        String nodeSelector = object.toString().toUpperCase(Locale.ENGLISH);
        return switch (nodeSelector) {
            case "SKIP_DEDICATED_CLUSTER_MANAGERS" ->
                    Optional.of(NodeSelector.SKIP_DEDICATED_CLUSTER_MANAGERS);
            case "ANY" -> Optional.of(NodeSelector.ANY);
            default -> Optional.empty();
        };
    }
}
