plugins {
    `java-library`
    id("io.micronaut.build.internal.opensearch-base")
}

dependencies {
    api(libs.managed.opensearch.testcontainers)
    implementation(mnTestResources.micronaut.test.resources.server)
    implementation(mn.micronaut.core.processor)
}

// We have to do this, as under Java 21 test-suite-kotlin will still use 17 (because of Kapt)
// And if this is compiled by 21, we won't be able to use it...
java {
    sourceCompatibility = JavaVersion.toVersion("21")
    targetCompatibility = JavaVersion.toVersion("21")
}
