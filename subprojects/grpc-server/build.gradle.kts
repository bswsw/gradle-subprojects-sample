val grpcStarterVersion: String by rootProject.extra

dependencies {
    implementation(project(":protocol"))
    implementation("io.github.lognet:grpc-spring-boot-starter:$grpcStarterVersion")
}
