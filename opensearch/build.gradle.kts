plugins {
    id("io.micronaut.build.internal.opensearch-module")
}

dependencies {
    compileOnly(mn.micronaut.management)

    api(libs.managed.opensearch.java)
}

micronautBuild {
    // new module, so no binary check
    binaryCompatibility {
        enabled.set(false)
    }
}
