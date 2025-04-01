package micronaut.example.service

import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.ReflectiveAccess

@ReflectiveAccess
@Introspected
data class Movie(val imdb: String, val title: String)
