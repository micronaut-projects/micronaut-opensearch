plugins {
    `java-library`
    id("io.micronaut.build.internal.opensearch-base")
    id("io.micronaut.build.internal.python")
}

// The examples of the guide, in Python, compiled by the Python compiler (micronaut-inject-python). The compiler
// takes the (jar-resolved) compile classpath as its annotation processor path, so the Micronaut processors are
// regular test dependencies rather than annotationProcessor ones. The Python tests only run with -Ppython-ci.
dependencies {
    // The Java test helper (the @ContextConfigurer supplying the OpenSearch container properties) is processed by javac
    testAnnotationProcessor(platform(mn.micronaut.core.bom))
    testAnnotationProcessor(mn.micronaut.inject.java)

    testImplementation(platform(mn.micronaut.core.bom))
    testImplementation(platform(mnTest.micronaut.test.bom))
    testImplementation(mn.micronaut.inject.python.test)
    testImplementation(mn.micronaut.context.python)
    testImplementation(mnValidation.micronaut.validation.processor)

    testImplementation(projects.micronautOpensearchRestclient)
    testImplementation(projects.micronautOpensearchAmazon)
    testImplementation(mn.micronaut.jackson.databind)
    testImplementation(mn.micronaut.management)
    testImplementation(mn.micronaut.http.client)
    testImplementation(mn.micronaut.http.server.netty)
    testImplementation(mnValidation.micronaut.validation)
    testImplementation(mnTest.micronaut.test.junit5)

    testImplementation(projects.testSuiteOpensearchContainer)

    testRuntimeOnly(mnLogging.logback.classic)
    testRuntimeOnly(mnTest.junit.jupiter.engine)
    testRuntimeOnly(mnTest.junit.platform.launcher)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    systemProperty("micronaut.python.pool.enabled", "false")
}
