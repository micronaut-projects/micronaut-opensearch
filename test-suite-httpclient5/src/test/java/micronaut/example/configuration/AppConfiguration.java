package micronaut.example.configuration;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("app")
public interface AppConfiguration {
    String getMoviesIndexName();
}
