package example.micronaut.exception

class MovieServiceException(message: String?, cause: Throwable?) :
    RuntimeException(message, cause)
