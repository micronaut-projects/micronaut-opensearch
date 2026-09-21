import time
from typing import Annotated

from jakarta.inject import Inject
from micronaut.test.extensions.junit5.annotation import MicronautTest
from org.junit.jupiter.api import Test

from micronaut.example.service.Movie import Movie
from micronaut.example.service.MovieService import MovieService


# The OpenSearch container address is supplied by micronaut.example.support.OpenSearchTestConfigurer for the
# "opensearch" environment
@MicronautTest(environments=["opensearch"])
class OpenSearchTest:
    movie_service: Annotated[MovieService, Inject]

    @Test
    def test_open_search(self) -> None:
        imdb = "KJFDOD"
        title = "Die Hard"
        self.movie_service.save_movie(Movie(imdb, title))

        deadline = time.time() + 10
        while self.movie_service.search_movies(title) is None and time.time() < deadline:
            time.sleep(0.5)

        result = self.movie_service.search_movies(title)
        assert result is not None
        assert result.title == title
        assert result.imdb == imdb
