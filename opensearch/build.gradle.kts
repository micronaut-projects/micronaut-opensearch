plugins {
    id("io.micronaut.build.internal.opensearch-module")
}
dependencies {
    api(libs.managed.opensearch.java)
    compileOnly(mn.micronaut.management)
}

micronautBuild {
    // new module, so no binary check
    binaryCompatibility {
        enabled.set(false)
    }
}