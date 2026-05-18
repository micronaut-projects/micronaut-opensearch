plugins {
    `java-library`
    id("io.micronaut.build.internal.opensearch-base")
}

dependencies {
    implementation(platform(mnTest.boms.testcontainers))
    implementation(libs.testcontainers)
    implementation(libs.managed.opensearch.testcontainers)
    implementation(mn.micronaut.core.processor)
}

java {
    sourceCompatibility = JavaVersion.toVersion("25")
    targetCompatibility = JavaVersion.toVersion("25")
}
