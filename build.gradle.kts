plugins {
    id("org.springframework.boot") version "2.1.8.RELEASE" apply false

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
    }
}

val grpcStarterVersion by extra { "3.4.3" }
val protobufVersion by extra { "3.10.0" }
val grpcVersion by extra { "1.24.0" }
val ktlintVersion by extra { "0.35.0" }

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

    val ktlintDependency by configurations.creating

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        compileOnly("org.springframework.boot:spring-boot-configuration-processor")
        kapt("org.springframework.boot:spring-boot-configuration-processor")

        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation(kotlin("reflect"))
        implementation(kotlin("stdlib-jdk8"))

        testImplementation("org.springframework.boot:spring-boot-starter-test")

        ktlintDependency("com.pinterest:ktlint:$ktlintVersion")
    }

    tasks {
        val ktlint by registering(JavaExec::class) {
            classpath = ktlintDependency
            main = "com.pinterest.ktlint.Main"
            args = listOf("src/**/*.kt")
            description = "Check Kotlin code style."
        }

        val ktlintFormat by registering(JavaExec::class) {
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
