package micronaut.example;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import micronaut.example.service.Movie;
import micronaut.example.service.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class OpenSearchTest {
    @Test
    void testOpenSearch(MovieService movieService) {
        String imdb = "KJFDOD";
        String title = "Die Hard";
        movieService.saveMovie(new Movie("KJFDOD", title));
        await().atMost(10, SECONDS).until(() ->
            movieService.searchMovies(title) != null
        );
        Movie result = movieService.searchMovies(title);
        Assertions.assertNotNull(result);
        assertEquals(title, result.getTitle());
        assertEquals(imdb, result.getImdb());
    }
}
