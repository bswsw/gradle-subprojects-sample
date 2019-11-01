val grpcVersion: String by rootProject.extra

dependencies {
    implementation(project(":env"))
    implementation(project(":domain:main"))
    implementation(project(":protocol"))

    implementation("io.grpc:grpc-netty:$grpcVersion")
    implementation("org.springframework.boot:spring-boot-starter-hateoas") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow")
}
