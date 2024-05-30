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
package io.micronaut.opensearch.httpclient5.converters;

import io.micronaut.core.annotation.Internal;
import io.micronaut.core.convert.MutableConversionService;
import io.micronaut.core.convert.TypeConverterRegistrar;
import org.apache.hc.core5.http.HttpHost;

/**
 * {@link TypeConverterRegistrar} for Apache HTTP classes conversions.
 * @author Sergio del Amo
 * @since 1.0.0
 */
@Internal
public final class HttpClient5TypeConverterRegistrar implements TypeConverterRegistrar {
    @Override
    public void register(MutableConversionService conversionService) {
        conversionService.addConverter(CharSequence.class, HttpHost.class, new StringToHttpHostConverter());
    }
}