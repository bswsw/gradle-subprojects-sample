pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}
rootProject.name = "gradle-subprojects-sample"

include(
    "domain:core",
    "domain:main",
    "domain:sub",
    "domain:common",
    "protocol",
    "env",
    "grpc-server",
    "api-server"
)

rootProject.children.forEach { group ->
    println("project group : ${group.name}")
    group.projectDir = file("subprojects/${group.name}")

    group.children.forEach { project ->
        println("${group.name} -> ${project.name}")
        project.projectDir = file("subprojects/${group.name}/${project.name}")
    }
}
