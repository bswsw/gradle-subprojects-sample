pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}
rootProject.name = "subproject-sample"

include(
    "server",
    "protocol",
    "client"
)

rootProject.children.forEach {
    it.projectDir = file("subprojects/${it.name}")
}
