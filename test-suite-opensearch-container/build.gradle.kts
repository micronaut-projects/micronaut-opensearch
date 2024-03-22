plugins {
    `java-library`
    id("io.micronaut.build.internal.opensearch-base")
}

dependencies {
    api(libs.managed.opensearch.testcontainers)
    implementation(mnTestResources.micronaut.test.resources.server)
}
