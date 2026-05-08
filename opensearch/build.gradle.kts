plugins {
    id("io.micronaut.build.internal.opensearch-module")
}

dependencies {
    compileOnly(mn.micronaut.management)

    api(libs.managed.opensearch.java)
    api(libs.managed.apache.httpcomponents.httpclient5) {
        because("opensearch-java 3.8.0 requests httpclient5 5.6, which is affected by GHSA-v468-qcjx-r72w")
    }
    constraints {
        runtimeOnly(libs.managed.jackson.core) {
            because("opensearch-java 3.8.0 requests jackson-core 2.20.1, which is affected by GHSA-72hv-8253-57qq")
        }
        runtimeOnly(libs.managed.jackson.databind) {
            because("Keep Jackson runtime components aligned with the constrained jackson-core version")
        }
    }
}
