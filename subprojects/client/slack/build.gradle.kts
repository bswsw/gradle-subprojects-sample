dependencies {
    implementation("org.springframework:spring-web")
}

tasks {
    bootJar {
        enabled = false
    }

    jar {
        enabled = true
    }
}
