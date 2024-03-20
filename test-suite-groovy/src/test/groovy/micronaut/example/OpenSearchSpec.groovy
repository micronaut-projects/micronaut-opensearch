package micronaut.example

import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import example.micronaut.service.Movie
import example.micronaut.service.MovieService
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

@MicronautTest
class OpenSearchSpec extends Specification {
    @Inject
    MovieService movieService

    void testOpenSearch() {
        given:
        String imdb = "KJFDOD"
        String title = "Die Hard"

        when:
        movieService.saveMovie(new Movie(imdb: "KJFDOD", title: title));

        then:
        new PollingConditions().eventually {
            assert movieService.searchMovies(title) != null
        }

        when:
        Movie result = movieService.searchMovies(title);

        then:
        result
        title == result.title
        imdb == result.imdb
    }
}
