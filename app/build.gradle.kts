plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android") version "1.9.10" // Make sure this matches your Kotlin version
}

android {
    namespace = "com.justbuzer.sol"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.justbuzer.sol"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    // Ensure Kotlin is targeting JVM 21, which should be valid with Kotlin 1.9.10
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.10")
    
    // JUnit 4 for unit tests
    testImplementation("junit:junit:4.13.2")

    // Optional: JUnit 5 (if needed for more advanced testing)
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.2")
}
