package micronaut.example;

import example.micronaut.service.Movie
import example.micronaut.service.MovieService
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import java.util.concurrent.TimeUnit
import org.awaitility.Awaitility.await
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

@MicronautTest
class OpenSearchTest {

    @Test
    fun testOpenSearch(movieService: MovieService) {
        val imdb = "KJFDOD"
        val title = "Die Hard"
        movieService.saveMovie(Movie("KJFDOD", title))
        await().atMost(10, TimeUnit.SECONDS).until {
            movieService.searchMovies(title) != null
        }
        val result: Movie? = movieService.searchMovies(title)

        assertNotNull(result)
        assertEquals(title, result!!.title)
        assertEquals(imdb, result.imdb)
    }
}
