from dataclasses import dataclass

from micronaut.core.annotation import Introspected


@Introspected
@dataclass
class Movie:
    imdb: str | None = None
    title: str | None = None
