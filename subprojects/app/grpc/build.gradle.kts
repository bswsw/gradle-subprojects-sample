val grpcStarterVersion: String by rootProject.extra
val grpcVersion: String by rootProject.extra

dependencies {
    implementation(project(":env"))
    implementation(project(":protocol"))
    implementation(project(":domain:main"))
    implementation("io.github.lognet:grpc-spring-boot-starter:$grpcStarterVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.grpc:grpc-testing:$grpcVersion")
}
