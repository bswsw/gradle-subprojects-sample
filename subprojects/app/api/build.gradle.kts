dependencies {
    implementation(project(":env"))
    implementation(project(":domain:main"))

    implementation("org.springframework.boot:spring-boot-starter-hateoas") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow")
}
