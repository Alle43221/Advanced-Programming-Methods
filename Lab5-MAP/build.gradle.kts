plugins {
    id("java")
}

group = "ubb.scs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.postgresql:postgresql:42.2.23") // Update version as needed
}

tasks.test {
    useJUnitPlatform()
}