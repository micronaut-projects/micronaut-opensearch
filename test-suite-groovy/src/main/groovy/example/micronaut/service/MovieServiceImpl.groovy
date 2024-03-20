package example.micronaut.service

import example.micronaut.configuration.AppConfiguration
import example.micronaut.exception.MovieServiceException
import jakarta.inject.Singleton
import org.opensearch.client.opensearch.OpenSearchClient
import org.opensearch.client.opensearch.core.IndexRequest
import org.opensearch.client.opensearch.core.IndexResponse
import org.opensearch.client.opensearch.core.SearchResponse
import org.opensearch.client.opensearch.core.search.Hit
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class MovieServiceImpl implements MovieService {

    private static final Logger LOG = LoggerFactory.getLogger(MovieServiceImpl.class)

    private final AppConfiguration appConfiguration

    private final OpenSearchClient client

    MovieServiceImpl(AppConfiguration appConfiguration, OpenSearchClient client) {
        this.appConfiguration = appConfiguration
        this.client = client
    }

    @Override
    String saveMovie(Movie movie) {
        try {
            IndexRequest<Movie> indexRequest = createIndexRequest(movie)

            IndexResponse indexResponse = client.index(indexRequest)
            String id = indexResponse.id()

            LOG.info("Document for '{}' {} successfully in ES. The id is: {}", movie, indexResponse.result(), id)
            return id
        } catch (Exception e) {
            String errorMessage = String.format("An exception occurred while indexing '%s'", movie)
            LOG.error(errorMessage)
            throw new MovieServiceException(errorMessage, e)
        }
    }

    private IndexRequest<Movie> createIndexRequest(Movie movie) {
        return new IndexRequest.Builder<Movie>()
            .index(appConfiguration.getMoviesIndexName())
            .document(movie)
            .build()
    }

    @Override
    Movie searchMovies(String title) {
        try {
            SearchResponse<Movie> searchResponse = client.search((s) ->
                s.index(appConfiguration.getMoviesIndexName())
                    .query(q -> q.match(m ->
                        m.field("title")
                         .query(fq -> fq.stringValue(title))
                    )), Movie.class
            )
            LOG.info("Searching for '{}' took {} and found {}", title, searchResponse.took(), searchResponse.hits().total().value())

            Iterator<Hit<Movie>> hits = searchResponse.hits().hits().iterator()
            if (hits.hasNext()) {
                return hits.next().source()
            }
            return null

        } catch (Exception e) {
            String errorMessage = String.format("An exception occurred while searching for title '%s'", title)
            LOG.error(errorMessage)
            throw new MovieServiceException(errorMessage, e)
        }
    }
}
