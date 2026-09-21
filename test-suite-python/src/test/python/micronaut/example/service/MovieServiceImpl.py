import logging

from jakarta.inject import Singleton
from java.lang import Exception as JavaException
from org.opensearch.client.opensearch import OpenSearchClient
from org.opensearch.client.opensearch.core import IndexRequest

from micronaut.example.configuration.AppConfiguration import AppConfiguration
from micronaut.example.exception.MovieServiceException import MovieServiceException
from micronaut.example.service.Movie import Movie
from micronaut.example.service.MovieService import MovieService

LOG = logging.getLogger(__name__)


@Singleton
class MovieServiceImpl(MovieService):

    def __init__(self, app_configuration: AppConfiguration, client: OpenSearchClient):
        self.app_configuration = app_configuration
        self.client = client

    def save_movie(self, movie: Movie) -> str:
        try:
            index_request = self.create_index_request(movie)
            index_response = self.client.index(index_request)
            id = index_response.id()
            LOG.info("Document for '%s' %s successfully in ES. The id is: %s", movie, index_response.result(), id)
            return id
        except JavaException as e:
            error_message = f"An exception occurred while indexing '{movie}'"
            LOG.error(error_message)
            raise MovieServiceException(error_message, e)

    def create_index_request(self, movie: Movie) -> IndexRequest[Movie]:
        return (IndexRequest.Builder()
                .index(self.app_configuration.movies_index_name)
                .document(movie)
                .build())

    def search_movies(self, title: str) -> Movie | None:
        try:
            search_response = self.client.search(
                lambda s: s.index(self.app_configuration.movies_index_name).query(
                    lambda q: q.match(
                        lambda m: m.field("title").query(lambda fq: fq.stringValue(title)))),
                Movie)
            LOG.info("Searching for '%s' took %s and found %s",
                     title,
                     search_response.took(),
                     search_response.hits().total().value())
            hits = search_response.hits().hits()
            if not hits.isEmpty():
                return hits.get(0).source()
            return None
        except JavaException as e:
            error_message = f"An exception occurred while searching for title '{title}'"
            LOG.error(error_message)
            raise MovieServiceException(error_message, e)
