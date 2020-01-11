plugins {
    id("org.asciidoctor.convert") version "2.4.0"
}

dependencies {
    implementation(project(":auto-env"))
    implementation(project(":domain:core"))
    implementation(project(":client:slack"))

    implementation("org.springframework.boot:spring-boot-starter-hateoas") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow") {
        exclude(group = "io.undertow", module = "undertow-websockets-jsr")
    }

    implementation("org.springframework.boot:spring-boot-starter-actuator")

    asciidoctor("org.springframework.restdocs:spring-restdocs-asciidoctor")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

val snippetDir = file("build/generated-snippets")

tasks {
    test {
        outputs.dir(snippetDir)
    }

    asciidoctor {
        inputs.dir(snippetDir)
        dependsOn(test)
    }

    bootJar {
        dependsOn(asciidoctor)
        from("${asciidoctor.get().outputDir}/html") {
            into("static/docs")
        }
    }
}
