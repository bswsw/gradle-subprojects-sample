val querydslVersion: String by rootProject.extra

dependencies {
    api(project(":domain:common"))
    runtimeOnly("mysql:mysql-connector-java")
    kapt("com.querydsl:querydsl-apt:$querydslVersion:jpa")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testRuntimeOnly("com.h2database:h2")
}

tasks {
    bootJar {
        enabled = false
    }

    jar {
        enabled = true
    }
}
