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
    kaptTest(mn.micronaut.inject.java)
    kaptTest(mnValidation.micronaut.validation.processor)

    testImplementation(libs.awaitility)
    testImplementation(libs.kotlin.reflect)
    testImplementation(libs.kotlin.stdlib.jdk8)

    testImplementation(mn.micronaut.http.client)
    testImplementation(mn.micronaut.http.server.netty)
    testImplementation(mn.micronaut.jackson.databind)
    testImplementation(mn.micronaut.management)

    testImplementation(mnKotlin.micronaut.kotlin.runtime)
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
