import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.31"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

configurations {}

val cucumberRuntime by configurations.creating {
    extendsFrom(configurations["testImplementation"])
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("io.cucumber:cucumber-java:4.3.1")
    testImplementation("io.cucumber:cucumber-junit:4.3.1")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

task("cucumber") {
    dependsOn("assemble", "compileTestJava")
    doLast {
        javaexec {
            main = "cucumber.api.cli.Main"
            classpath = cucumberRuntime + sourceSets.main.get().output + sourceSets.test.get().output
            args = listOf("--plugin", "pretty", "--glue", "com.example.feature", "src/test/resources")
        }
    }
}
