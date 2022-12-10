/*
 * This file was generated by the Gradle 'init' task.
 */



plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    api("com.google.code.findbugs:jsr305:3.0.2")
}

group = "com.friska"
version = "1.0-SNAPSHOT"
description = "Minecraft Resource Manager"
java.sourceCompatibility = JavaVersion.VERSION_16

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}