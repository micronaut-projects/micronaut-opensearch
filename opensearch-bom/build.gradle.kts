plugins {
    id("io.micronaut.build.internal.opensearch-base")
    id("io.micronaut.build.internal.bom")
}
micronautBuild {
    // new module, so no binary check
    binaryCompatibility {
        enabled.set(false)
    }
}