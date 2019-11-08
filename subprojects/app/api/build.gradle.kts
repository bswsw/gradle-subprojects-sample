dependencies {
    implementation(project(":env"))
    implementation(project(":domain:core"))
    implementation(project(":domain:main"))
    implementation(project(":domain:sub"))

    implementation("org.springframework.boot:spring-boot-starter-hateoas") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow") {
        exclude(group = "io.undertow", module = "undertow-websockets-jsr")
    }
}
