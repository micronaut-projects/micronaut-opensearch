pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("io.micronaut.build.shared.settings") version "7.1.1"
}

rootProject.name = "opensearch-parent"

include("opensearch")
include("opensearch-restclient")
include("opensearch-httpclient5")
include("opensearch-amazon")
include("opensearch-bom")
include("test-suite-httpclient5")
include("test-suite")
include("test-suite-groovy")
include("test-suite-kotlin")
include("test-suite-opensearch-container")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

configure<io.micronaut.build.MicronautBuildSettingsExtension> {
    useStandardizedProjectNames.set(true)
    importMicronautCatalog()
    importMicronautCatalog("micronaut-aws")
    importMicronautCatalog("micronaut-kotlin")
    importMicronautCatalog("micronaut-serde")
    importMicronautCatalog("micronaut-test-resources")
    importMicronautCatalog("micronaut-validation")
}
