val grpcStarterVersion: String by rootProject.extra

dependencies {
    implementation(project(":protocol"))
    implementation(project(":domain:main"))
    implementation("io.github.lognet:grpc-spring-boot-starter:$grpcStarterVersion")
}
