package micronaut.example.service;

import io.micronaut.core.annotation.Introspected;

@Introspected
public record Movie(String imdb, String title) {
}
