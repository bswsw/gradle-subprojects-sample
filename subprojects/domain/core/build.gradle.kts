dependencies {
    implementation(project(":domain:main"))
    implementation(project(":domain:sub"))
}

tasks {
    bootJar {
        enabled = false
    }

    jar {
        enabled = true
    }
}
