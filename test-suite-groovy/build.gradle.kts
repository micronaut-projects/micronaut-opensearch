plugins {
    id("groovy")
    id("io.micronaut.minimal.application")
    id("io.micronaut.test-resources")
}

dependencies {
    testCompileOnly(mnValidation.micronaut.validation.processor)

    testImplementation(libs.managed.opensearch.rest.client) {
        exclude(group = "commons-logging", module = "commons-logging")
    }
    testImplementation(libs.micronaut.runtime.groovy)

    testImplementation(projects.micronautOpensearchRestclient)
    testImplementation(mn.micronaut.jackson.databind)
    testImplementation(mn.micronaut.management)
    testImplementation(mn.micronaut.http.client)
    testImplementation(mnValidation.micronaut.validation)

    testRuntimeOnly(mnLogging.logback.classic)
    testRuntimeOnly(mnLogging.slf4j.jcl.over.slf4j)

    testResourcesImplementation(projects.testSuiteOpensearchContainer)
}

micronaut {
    version.set(libs.versions.micronaut.platform)
    runtime("netty")
    testRuntime("spock")
    testResources {
        clientTimeout = 300
    }
}

