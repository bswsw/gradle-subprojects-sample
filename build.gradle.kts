plugins {
    id("org.springframework.boot") version "2.1.6.RELEASE" apply false

    val kotlinVersion = "1.3.41"

    kotlin("jvm") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion apply false
    kotlin("plugin.jpa") version kotlinVersion apply false
    kotlin("plugin.noarg") version kotlinVersion apply false
    kotlin("plugin.allopen") version kotlinVersion apply false
}

allprojects {
    repositories {
        mavenCentral()
    }
}

val grpcStarterVersion by extra { "3.3.0" }
val protobufVersion by extra { "3.7.1" }
val grpcVersion by extra { "1.21.0" }
val ktlintVersion by extra { "0.33.0" }

subprojects {
    apply {
        plugin("io.spring.dependency-management")
        plugin("org.springframework.boot")
        plugin("kotlin")
        plugin("kotlin-kapt")
        plugin("kotlin-spring")
        plugin("kotlin-jpa")
        plugin("kotlin-noarg")
        plugin("kotlin-allopen")
        plugin("idea")
    }

    group = "com.baegoon"
    version = "0.0.1-SNAPSHOT"
//    java.sourceCompatibility = JavaVersion.VERSION_1_8

    val ktlint by configurations.creating

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")

        compileOnly("org.springframework.boot:spring-boot-configuration-processor")
        kapt("org.springframework.boot:spring-boot-configuration-processor")

        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation(kotlin("reflect"))
        implementation(kotlin("stdlib-jdk8"))

        testImplementation("org.springframework.boot:spring-boot-starter-test")

        ktlint("com.pinterest:ktlint:$ktlintVersion")
    }

    tasks {
        compileKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "1.8"
            }

            dependsOn("ktlint")
        }

        compileTestKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "1.8"
            }
        }

        register<JavaExec>("ktlint") {
            classpath = ktlint
            main = "com.pinterest.ktlint.Main"
            args = listOf("src/**/*.kt")
            description = "Check Kotlin code style."
        }
    }
}
