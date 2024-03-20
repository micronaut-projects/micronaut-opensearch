plugins {
    id("io.micronaut.build.internal.opensearch-module")
}
dependencies {
    api(libs.managed.opensearch.java)
    compileOnly(mn.micronaut.management)
    compileOnly(libs.managed.opensearch.rest.client)
    testImplementation(libs.managed.opensearch.rest.client) {
        exclude(group="commons-logging", module = "commons-logging")
    }
    testRuntimeOnly(mnLogging.slf4j.jcl.over.slf4j)
    compileOnly(mn.micronaut.jackson.databind)

    testImplementation(mn.micronaut.jackson.databind)

    testImplementation(mn.micronaut.management)
    testAnnotationProcessor(mn.micronaut.inject.java)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(mnTest.micronaut.test.junit5)
    testRuntimeOnly(libs.junit.jupiter.engine)
}

micronautBuild {
    // new module, so no binary check
    binaryCompatibility {
        enabled.set(false)
    }
}