pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}
rootProject.name = "gradle-subprojects-sample"

include(
    "domain",
    "protocol",
    "env",
    "grpc-server",
    "api-server"
)

rootProject.children.forEach {
    it.projectDir = file("subprojects/${it.name}")
}
