plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.allopen)
    id("io.micronaut.test-resources")
    id("io.micronaut.graalvm")
}

repositories {
    mavenCentral()
}

dependencies {
    kapt(mn.micronaut.inject.java)
    kapt(mnValidation.micronaut.validation.processor)

    testImplementation(projects.opensearchRestclient)

    testImplementation(mn.micronaut.jackson.databind)
    testImplementation(mn.micronaut.management)

    testImplementation(mnKotlin.micronaut.kotlin.runtime)
    testImplementation(libs.kotlin.stdlib.jdk8)
    testImplementation(libs.kotlin.reflect)

    testImplementation(mnValidation.micronaut.validation)

    runtimeOnly(mnLogging.logback.classic)

    kaptTest(mn.micronaut.inject.java)

    testImplementation(platform(mnTest.boms.junit))
    testImplementation(mn.micronaut.http.client)
    testImplementation(mn.micronaut.http.server.netty)
    testImplementation(mnTest.micronaut.test.junit5)
    testImplementation(libs.awaitility)

    testRuntimeOnly(libs.junit.jupiter.engine)

    testResourcesImplementation(libs.managed.opensearch.testcontainers)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

micronaut {
    version.set(libs.versions.micronaut.platform.get())
    testResources {
        clientTimeout = 300
    }
}
