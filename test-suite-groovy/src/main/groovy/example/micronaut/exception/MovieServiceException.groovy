package example.micronaut.exception

class MovieServiceException extends RuntimeException {

    MovieServiceException(String message, Throwable cause) {
        super(message, cause)
    }
}
