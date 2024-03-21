plugins {
    id("io.micronaut.build.internal.opensearch-module")
}
dependencies {
    api(projects.opensearch)
    api(libs.managed.apache.httpcomponents.httpclient5)
}
micronautBuild {
    // new module, so no binary check
    binaryCompatibility {
        enabled.set(false)
    }
}