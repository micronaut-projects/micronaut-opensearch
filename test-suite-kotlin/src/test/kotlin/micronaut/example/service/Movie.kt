package micronaut.example.service

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected

@Introspected
data class Movie(@JsonProperty("imdb") val imdb: String, @JsonProperty("title") val title: String)
