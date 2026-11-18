plugins {
    id("io.micronaut.build.internal.opensearch-module")
}

dependencies {
    api(projects.micronautOpensearch)
    api(platform(mnAws.boms.aws.java.sdk.v2))
    api(mnAws.micronaut.aws.sdk.v2)
    api(libs.awssdk.auth)
    api(libs.awssdk.regions)
    api(libs.awssdk.apache.client)

    constraints {
        api(libs.managed.jackson3.core) {
            because("Use the fixed Jackson 3.1.7 release for upstream-owned security advisories")
        }
        api(libs.managed.jackson3.databind) {
            because("Keep Jackson 3 runtime components aligned on the fixed 3.1.7 release")
        }
    }
}
