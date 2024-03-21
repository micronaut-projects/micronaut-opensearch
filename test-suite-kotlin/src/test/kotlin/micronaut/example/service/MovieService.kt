package micronaut.example.service

interface MovieService {
    fun saveMovie(movie: Movie): String
    fun searchMovies(title: String): Movie?
}