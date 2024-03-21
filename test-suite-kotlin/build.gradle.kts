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

    implementation(projects.opensearch)

    implementation(mn.micronaut.jackson.databind)
    implementation(mn.micronaut.management)

    implementation(mnKotlin.micronaut.kotlin.runtime)
    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.kotlin.reflect)

    implementation(mnValidation.micronaut.validation)

    runtimeOnly(mnLogging.logback.classic)

    kaptTest(mn.micronaut.inject.java)

    testImplementation(platform(mnTest.boms.junit))
    testImplementation(mn.micronaut.http.client)
    testImplementation(mn.micronaut.http.server.netty)
    testImplementation(mnTest.micronaut.test.junit5)
    testImplementation(libs.awaitility)

    testRuntimeOnly(libs.junit.jupiter.engine)

    testResourcesImplementation(libs.opensearch.testcontainers)
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
