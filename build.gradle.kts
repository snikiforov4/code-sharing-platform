plugins {
    java
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

group = "edu.snykyforov"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    val lombokVersion: String by project
    val junitVersion: String by project
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-freemarker")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.google.code.findbugs:jsr305:3.0.2")
    runtimeOnly("com.h2database:h2")
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")


    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testCompileOnly("org.projectlombok:lombok:$lombokVersion")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}