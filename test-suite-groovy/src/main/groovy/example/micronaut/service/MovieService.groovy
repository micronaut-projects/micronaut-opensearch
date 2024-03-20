package example.micronaut.service

interface MovieService {
    String saveMovie(Movie movie)

    Movie searchMovies(String title)
}
