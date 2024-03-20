package example.micronaut.service

import example.micronaut.configuration.AppConfiguration
import example.micronaut.exception.MovieServiceException
import jakarta.inject.Singleton
import org.opensearch.client.opensearch.OpenSearchClient
import org.opensearch.client.opensearch._types.FieldValue
import org.opensearch.client.opensearch._types.query_dsl.MatchQuery
import org.opensearch.client.opensearch._types.query_dsl.Query
import org.opensearch.client.opensearch.core.IndexRequest
import org.opensearch.client.opensearch.core.IndexResponse
import org.opensearch.client.opensearch.core.SearchRequest
import org.opensearch.client.opensearch.core.SearchResponse
import org.opensearch.client.opensearch.core.search.Hit
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Singleton
class MovieServiceImpl(
    private val appConfiguration: AppConfiguration,
    private val client: OpenSearchClient
) : MovieService {

    override fun saveMovie(movie: Movie): String {
        try {
            val indexRequest: IndexRequest<Movie> = createIndexRequest(movie)
            val indexResponse: IndexResponse = client.index(indexRequest)
            val id = indexResponse.id()
            LOG.info("Document for '{}' {} successfully in ES. The id is: {}", movie, indexResponse.result(), id)
            return id
        } catch (e: Exception) {
            val errorMessage = String.format("An exception occurred while indexing '%s'", movie)
            LOG.error(errorMessage)
            throw MovieServiceException(errorMessage, e)
        }
    }

    private fun createIndexRequest(movie: Movie) = IndexRequest.Builder<Movie>()
            .index(appConfiguration.moviesIndexName)
            .document(movie)
            .build()

    override fun searchMovies(title: String): Movie? {
        try {
            val searchResponse: SearchResponse<Movie> =
                client.search({ s: SearchRequest.Builder ->
                    s.index(appConfiguration.moviesIndexName)
                        .query { q: Query.Builder ->
                            q.match { m: MatchQuery.Builder ->
                                m.field("title")
                                    .query { fq: FieldValue.Builder ->
                                        fq.stringValue(
                                            title
                                        )
                                    }
                            }
                        }
                }, Movie::class.java)
            LOG.info(
                "Searching for '{}' took {} and found {}",
                title,
                searchResponse.took(),
                searchResponse.hits().total().value()
            )
            val hits: Iterator<Hit<Movie>> = searchResponse.hits().hits().iterator()
            if (hits.hasNext()) {
                return hits.next().source()
            }
            return null
        } catch (e: Exception) {
            val errorMessage = String.format("An exception occurred while searching for title '%s'", title)
            LOG.error(errorMessage)
            throw MovieServiceException(errorMessage, e)
        }
    }

    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(MovieServiceImpl::class.java)
    }
}
