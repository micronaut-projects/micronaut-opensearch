package example.micronaut.configuration

import io.micronaut.context.annotation.ConfigurationProperties

@ConfigurationProperties("app")
interface AppConfiguration {

    val moviesIndexName: String?
}
