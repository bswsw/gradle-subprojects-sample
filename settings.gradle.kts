pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}
rootProject.name = "gradle-subprojects-sample"

include(
    "grpc-server",
    "protocol",
    "api-server"
)

rootProject.children.forEach {
    it.projectDir = file("subprojects/${it.name}")
}
