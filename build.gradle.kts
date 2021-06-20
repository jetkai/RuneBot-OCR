import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    kotlin("plugin.serialization") version "1.5.10"
    application
}

group = "ocr.OCR"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.register<Copy>("copyJsonQuestions") {
    from(layout.buildDirectory.file("data/questions.json"))
    into(layout.buildDirectory.dir("toArchive"))
}

tasks.withType<Jar> {
    manifest {
        attributes["Implementation-Title"] = project.name
        attributes["Implementation-Version"] = project.version
        attributes["Main-Class"] = "ocr.OCR"
    }
    from(sourceSets.main.get().output)
        dependsOn(configurations.runtimeClasspath)
    from({ configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) } })
        application { mainClassName = "ocr.OCR"}
}

tasks {
    "build" {
        dependsOn()
    }
}

tasks.jar {
    manifest {
        attributes(mapOf("Implementation-Title" to project.name, "Implementation-Version" to project.version))
    }
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.1")
    implementation("org.jboss.netty:netty:3.2.0.Final")
    implementation("org.json:json:20210307")
    implementation("com.1stleg:jnativehook:2.1.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "OCR"
}