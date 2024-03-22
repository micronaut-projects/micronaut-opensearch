plugins {
    id("io.micronaut.build.internal.opensearch-module")
}

dependencies {
    compileOnly(mn.micronaut.jackson.databind)

    api(projects.micronautOpensearch)
    api(libs.managed.opensearch.rest.client) {
        exclude(group="commons-logging", module = "commons-logging")
    }

    runtimeOnly(mnLogging.slf4j.jcl.over.slf4j)

    testImplementation(mn.micronaut.jackson.databind)
}

micronautBuild {
    // new module, so no binary check
    binaryCompatibility {
        enabled.set(false)
    }
}
