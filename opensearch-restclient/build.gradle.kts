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

    constraints {
        implementation("org.bouncycastle:bc-fips:2.0.1"){
            because("Older versions have CVE(CVE-2025-8885) Vulnerability")
        }
    }
}

micronautBuild {
    // new module, so no binary check
    binaryCompatibility {
        enabled.set(false)
    }
}
