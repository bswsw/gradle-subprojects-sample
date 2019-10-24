val grpcStarterVersion: String by rootProject.extra

dependencies {
    implementation(project(":protocol"))
    implementation(project(":domain"))
    implementation("io.github.lognet:grpc-spring-boot-starter:$grpcStarterVersion")
}
