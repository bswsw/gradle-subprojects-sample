import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    base

    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    id("org.springframework.boot") version "2.1.6.RELEASE"

    val kotlinVersion = "1.3.40"

    kotlin("jvm") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("plugin.noarg") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion
}

allprojects {
    group = "com.baegoon"
    version = "0.0.1-SNAPSHOT"

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
        plugin("kotlin-spring")
        plugin("idea")
    }

    java.sourceCompatibility = JavaVersion.VERSION_1_8

    val ktlint by configurations.creating

    dependencies {
        implementation(kotlin("reflect"))
        implementation(kotlin("stdlib-jdk8"))

        ktlint("com.pinterest:ktlint:$ktlintVersion")
    }

    tasks {
        withType<BootJar> {
            enabled = false
        }
        withType<Jar> {
            enabled = true
        }

        withType<GradleBuild> {
            dependsOn("clean")
        }

        withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "1.8"
            }

            dependsOn("ktlint")
        }

        register<JavaExec>("ktlint") {
            classpath = ktlint
            main = "com.pinterest.ktlint.Main"
            args = listOf("src/**/*.kt")
            description = "Check Kotlin code style."
        }
    }
}
