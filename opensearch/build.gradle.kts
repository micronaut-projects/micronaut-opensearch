plugins {
    id("io.micronaut.build.internal.opensearch-module")
}
dependencies {
    annotationProcessor(mn.micronaut.graal)

    compileOnly(libs.graal.svm)
    compileOnly(mn.micronaut.management)
    api(libs.managed.opensearch.java)
    
    implementation(libs.managed.opensearch.rest.client) {
        exclude(group="commons-logging", module = "commons-logging")
    }
    runtimeOnly(mnLogging.slf4j.jcl.over.slf4j)
    api(mn.micronaut.http)

    implementation(mn.micronaut.jackson.databind)

    testAnnotationProcessor(mn.micronaut.inject.java)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(mnTest.micronaut.test.junit5)
    testRuntimeOnly(libs.junit.jupiter.engine)
}

tasks {
    named<Test>("test") {
        systemProperty("opensearch.version", libs.versions.managed.opensearch.rest.client.get())
    }
}

micronautBuild {
    // new module, so no binary check
    binaryCompatibility {
        enabled.set(false)
    }
}