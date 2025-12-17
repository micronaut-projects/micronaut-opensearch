plugins {
    id("io.micronaut.build.internal.kotlin-kapt")
    id("io.micronaut.test-resources")
    id("io.micronaut.graalvm")
}

repositories {
    mavenCentral()
}

dependencies {
    kaptTest(mn.micronaut.inject.java)
    kaptTest(mnValidation.micronaut.validation.processor)

    testImplementation(libs.awaitility)

    testImplementation(mn.micronaut.http.client)
    testImplementation(mn.micronaut.http.server.netty)
    testImplementation(mn.micronaut.jackson.databind)
    testImplementation(mn.micronaut.management)

    testImplementation(mnTest.micronaut.test.junit5)
    testImplementation(mnValidation.micronaut.validation)

    testImplementation(platform(mnTest.boms.junit))
    testImplementation(projects.micronautOpensearchRestclient)

    testRuntimeOnly(mnLogging.logback.classic)
    testRuntimeOnly(libs.junit.jupiter.engine)

    testResourcesImplementation(projects.testSuiteOpensearchContainer)
}

tasks.test {
    useJUnitPlatform()
}


micronaut {
    version.set(libs.versions.micronaut.platform.get())
    testResources {
        clientTimeout = 300
    }
}
