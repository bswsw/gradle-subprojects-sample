dependencies {
    api(project(":domain:main"))
    api(project(":domain:sub"))
}

tasks {
    bootJar {
        enabled = false
    }

    jar {
        enabled = true
    }
}
