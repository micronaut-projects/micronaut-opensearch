package io.micronaut.opensearch.conditon;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.opensearch.client.RestClient;

import io.micronaut.context.annotation.Requires;
import io.micronaut.opensearch.OpenSearchSettings;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PACKAGE, ElementType.TYPE })
@Requires(property = OpenSearchSettings.PREFIX)
@Requires(classes = { RestClient.class })
public @interface RequiresOpenSearch {
}
