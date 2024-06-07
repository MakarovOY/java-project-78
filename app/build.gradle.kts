plugins {
    application
    jacoco
    id ("checkstyle")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    compileOnly ("org.projectlombok:lombok:1.18.20")
    annotationProcessor ("org.projectlombok:lombok:1.18.20")
    testCompileOnly ("org.projectlombok:lombok:1.18.20")
    testAnnotationProcessor ("org.projectlombok:lombok:1.18.20")
    testImplementation ("org.assertj:assertj-core:3.19.0")
}
jacoco {
    toolVersion = "0.8.11"
}

tasks.test {
    useJUnitPlatform()
}
tasks.jacocoTestReport {
    reports {

        xml.required.set(true)


    }
}