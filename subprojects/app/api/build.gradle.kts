dependencies {
    implementation(project(":env"))
    implementation(project(":domain:core"))
    implementation(project(":client:slack"))

    implementation("org.springframework.boot:spring-boot-starter-hateoas") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow") {
        exclude(group = "io.undertow", module = "undertow-websockets-jsr")
    }

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}
