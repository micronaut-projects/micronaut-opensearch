package micronaut.example.service

import io.micronaut.core.annotation.Introspected

@Introspected
data class Movie(val imdb: String, val title: String)
