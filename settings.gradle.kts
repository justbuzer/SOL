pluginManagement {
    repositories {
        google()  // Needed for Android plugin
        mavenCentral()  // For general dependencies
        gradlePluginPortal()  // For Gradle plugins
    }
}

plugins {
    // Apply the foojay-resolver plugin to allow automatic download of JDKs
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "SOL"
include("app")
