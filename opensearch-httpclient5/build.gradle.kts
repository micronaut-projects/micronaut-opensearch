plugins {
    id("io.micronaut.build.internal.opensearch-module")
}

dependencies {
    api(projects.micronautOpensearch)
    implementation(libs.managed.apache.httpcomponents.httpclient5)
}
