plugins {
    id("io.micronaut.build.internal.opensearch-module")
}
dependencies {
    annotationProcessor(mn.micronaut.graal)

    compileOnly(libs.graal.svm)
    implementation(mn.micronaut.management)
    api(libs.managed.opensearch.java)
    
    implementation(libs.managed.opensearch.rest.client) {
        exclude(group="commons-logging", module = "commons-logging")
    }
    runtimeOnly(libs.jcl.over.slf4j)
    api(mn.micronaut.http)

    implementation(mn.micronaut.jackson.databind)

    testImplementation(mn.groovy.json)
    // testImplementation(mnSecurity.micronaut.security)
    testImplementation(mn.reactor)
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