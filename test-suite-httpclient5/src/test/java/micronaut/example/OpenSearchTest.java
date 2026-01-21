package micronaut.example;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.opensearch.testresources.OpenSearch;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import micronaut.example.service.Movie;
import micronaut.example.service.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Map;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OpenSearchTest implements TestPropertyProvider {

    @Override
    public @NonNull Map<String, String> getProperties() {
        return OpenSearch.getProperties();
    }

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
        assertEquals(title, result.title());
        assertEquals(imdb, result.imdb());
    }
}
