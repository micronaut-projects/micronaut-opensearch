plugins {
    id("io.micronaut.build.internal.opensearch-base")
    id("io.micronaut.build.internal.bom")
}
micronautBom {
    suppressions {
        acceptedVersionRegressions.add("apache-httpcomponents-httpclient5")
        acceptedVersionRegressions.add("jackson")
        acceptedLibraryRegressions.add("jackson-core")
        acceptedLibraryRegressions.add("jackson-databind")
        acceptedLibraryRegressions.add("apache-httpcomponents-httpclient5")
    }
}
