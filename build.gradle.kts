plugins {
    id("org.springframework.boot") version "2.2.0.RELEASE" apply false

    val kotlinVersion = "1.3.50"

    kotlin("jvm") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion apply false
    kotlin("plugin.jpa") version kotlinVersion apply false
    kotlin("plugin.noarg") version kotlinVersion apply false
    kotlin("plugin.allopen") version kotlinVersion apply false

    idea
}

allprojects {
    repositories {
        mavenCentral()
        maven {
            url = uri("http://localhost:8081/repository/maven-releases/")
        }
    }
}

val querydslVersion by extra { "4.2.1" }
val grpcStarterVersion by extra { "3.4.3" }
val protobufVersion by extra { "3.10.0" }
val grpcVersion by extra { "1.24.0" }
val ktlintVersion by extra { "0.35.0" }

subprojects {
    if (this.childProjects.isNotEmpty()) {
        return@subprojects
    }

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

    val ktlintDependency by configurations.creating

    dependencies {
        compileOnly("org.springframework.boot:spring-boot-configuration-processor")
        kapt("org.springframework.boot:spring-boot-configuration-processor")

        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation(kotlin("reflect"))
        implementation(kotlin("stdlib-jdk8"))

        ktlintDependency("com.pinterest:ktlint:$ktlintVersion")
    }

    tasks {
        val ktlint by registering(JavaExec::class) {
            classpath = ktlintDependency
            main = "com.pinterest.ktlint.Main"
            args = listOf("src/**/*.kt")
            description = "Check Kotlin code style."
        }

        register<JavaExec>("ktlintFormat") {
            classpath = ktlintDependency
            main = "com.pinterest.ktlint.Main"
            args = listOf("-F", "src/**/*.kt")
            description = "Fix Kotlin code style deviations."
        }

        compileKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "1.8"
            }

            dependsOn(ktlint)
        }

        compileTestKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "1.8"
            }
        }
    }
}
