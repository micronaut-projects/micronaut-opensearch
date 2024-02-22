plugins {
    id("io.micronaut.build.internal.opensearch-module")
}

micronautBuild {
    // new module, so no binary check
    binaryCompatibility {
        enabled.set(false)
    }
}