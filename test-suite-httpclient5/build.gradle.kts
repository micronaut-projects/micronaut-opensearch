plugins {
    id("io.micronaut.application")
    id("io.micronaut.test-resources")
}

dependencies {
    testAnnotationProcessor(mnSerde.micronaut.serde.processor)
    testAnnotationProcessor(mnValidation.micronaut.validation.processor)

    testImplementation(mnSerde.micronaut.serde.jackson)
    testImplementation(mnValidation.micronaut.validation)
    testImplementation(projects.micronautOpensearchHttpclient5)
    testImplementation(mn.micronaut.management)
    testImplementation(mn.micronaut.http.client)
    testImplementation(libs.awaitility)

    testRuntimeOnly(mnTest.junit.platform.suite)
    testRuntimeOnly(mnTest.junit.platform.launcher)
    testRuntimeOnly(mnTest.junit.jupiter.engine)

    testResourcesImplementation(projects.testSuiteOpensearchContainer)
}

micronaut {
    version.set(libs.versions.micronaut.platform)
    runtime("netty")
    testRuntime("junit5")
    testResources {
        clientTimeout = 300
    }
}

