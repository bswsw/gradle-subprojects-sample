val grpcVersion: String by rootProject.extra

dependencies {
    implementation(project(":env"))
    implementation(project(":domain"))
    implementation(project(":protocol"))
    implementation("io.grpc:grpc-netty:$grpcVersion")
    implementation("org.springframework.boot:spring-boot-starter-hateoas")
}
