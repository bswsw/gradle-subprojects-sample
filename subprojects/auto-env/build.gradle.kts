dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
}

tasks {
    bootJar {
        enabled = false
    }

    jar {
        enabled = true
    }
}
