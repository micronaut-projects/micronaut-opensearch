package micronaut.example.exception

class MovieServiceException(message: String, cause: Throwable) : RuntimeException(message, cause) {
}