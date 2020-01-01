val grpcStarterVersion: String by rootProject.extra
val grpcVersion: String by rootProject.extra

dependencies {
    implementation(project(":env"))
    implementation(project(":domain:core"))
    implementation("io.github.lognet:grpc-spring-boot-starter:$grpcStarterVersion")

    implementation("com.baegoon:protocol-module:1.0.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.grpc:grpc-testing:$grpcVersion")
}
