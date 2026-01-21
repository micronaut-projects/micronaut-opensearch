package micronaut.example.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

@Introspected
public record Movie(@JsonProperty("imdb") String imdb, @JsonProperty("title") String title) {
}
