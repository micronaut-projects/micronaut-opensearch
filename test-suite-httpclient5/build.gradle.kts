plugins {
    id("io.micronaut.application")
}

dependencies {
    testAnnotationProcessor(mnSerde.micronaut.serde.processor)
    testAnnotationProcessor(mnValidation.micronaut.validation.processor)
    testImplementation(platform(libs.micronaut.test))
    testImplementation(mnSerde.micronaut.serde.jackson)
    testImplementation(mnValidation.micronaut.validation)
    testImplementation(projects.micronautOpensearchHttpclient5)
    testImplementation(mn.micronaut.management)
    testImplementation(mn.micronaut.http.client)
    testImplementation(libs.awaitility)

    testImplementation(projects.testSuiteOpensearchContainer)
}

micronaut {
    version.set(libs.versions.micronaut.platform)
    runtime("netty")
    testRuntime("junit5")
}
