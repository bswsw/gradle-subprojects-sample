pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}
rootProject.name = "gradle-subprojects-sample"

include(
    "app:api",
    "app:grpc",
    "domain:core",
    "domain:main",
    "domain:sub",
    "domain:common",
    "client:slack",
    "auto-env"
)

makeProjectDir(rootProject, "subprojects")

fun makeProjectDir(project: ProjectDescriptor, group: String) {
    project.children.forEach {
        println("$group -> ${it.name}")

        it.projectDir = file("$group/${it.name}")
        makeProjectDir(it, "$group/${it.name}")
    }
}
