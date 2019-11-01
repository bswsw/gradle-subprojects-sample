val querydslVersion: String by rootProject.extra

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    api("com.querydsl:querydsl-jpa")
    kapt("com.querydsl:querydsl-apt:$querydslVersion:jpa")
}

tasks {
    bootJar {
        enabled = false
    }

    jar {
        enabled = true
    }
}
