plugins {
    id("io.micronaut.application")
    id("io.micronaut.test-resources")
}

dependencies {
    annotationProcessor(mnValidation.micronaut.validation.processor)

    implementation(mnValidation.micronaut.validation)
    implementation(projects.micronautOpensearchRestclient)
    implementation(mn.micronaut.jackson.databind)
    implementation(mn.micronaut.management)

    runtimeOnly(mnLogging.logback.classic)

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

