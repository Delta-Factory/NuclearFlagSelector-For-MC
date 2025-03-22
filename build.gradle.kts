plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

group = "delta.cion"
version = "0.0.1-DEV"
var main = "delta.cion.Main"

repositories {
    mavenCentral()
}

dependencies {
    implementation("commons-io:commons-io:2.5")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = main
    }
}

tasks.shadowJar {
    archiveClassifier.set("")
}

tasks.test {
    useJUnitPlatform()
}