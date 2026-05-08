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
}
