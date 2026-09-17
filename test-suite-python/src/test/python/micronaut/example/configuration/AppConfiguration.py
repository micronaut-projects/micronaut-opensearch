from micronaut.context.annotation import ConfigurationProperties


@ConfigurationProperties("app")
class AppConfiguration:
    movies_index_name: str | None = None
