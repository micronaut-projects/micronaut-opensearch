from abc import ABC, abstractmethod

from micronaut.example.service.Movie import Movie


class MovieService(ABC):

    @abstractmethod
    def save_movie(self, movie: Movie) -> str:
        ...

    @abstractmethod
    def search_movies(self, title: str) -> Movie | None:
        ...
