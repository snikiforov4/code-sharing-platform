pluginManagement {
    val springBootVersion: String by settings
    plugins {
        application
        java
        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version "1.0.11.RELEASE"
    }
}

rootProject.name = "code-sharing-platform"

