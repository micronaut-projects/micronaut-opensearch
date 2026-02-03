package micronaut.example

import io.micronaut.opensearch.testresources.OpenSearch
import micronaut.example.service.Movie
import micronaut.example.service.MovieService
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.micronaut.test.support.TestPropertyProvider
import java.util.concurrent.TimeUnit
import org.awaitility.Awaitility.await
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.condition.DisabledInNativeImage

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OpenSearchTest : TestPropertyProvider {

    @DisabledInNativeImage
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

    override fun getProperties(): Map<String, String> = OpenSearch.getProperties()
}
