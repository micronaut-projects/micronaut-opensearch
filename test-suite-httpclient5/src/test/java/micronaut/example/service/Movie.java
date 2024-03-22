package micronaut.example.service;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.ReflectiveAccess;

@Introspected
@ReflectiveAccess
public record Movie(String imdb, String title) {
}
