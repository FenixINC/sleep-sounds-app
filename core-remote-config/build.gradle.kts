plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 28
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    // Firebase Remote Config
    implementation(platform(notation = "com.google.firebase:firebase-bom:30.3.1"))
    implementation(dependencyNotation = "com.google.firebase:firebase-config-ktx")
    implementation(dependencyNotation = "com.google.firebase:firebase-analytics-ktx")

    // Dagger
    implementation(dependencyNotation = "com.google.dagger:dagger:2.43.2")
    kapt(dependencyNotation = "com.google.dagger:dagger-compiler:2.43.2")

    // Hilt
    implementation(dependencyNotation = "com.google.dagger:hilt-android:2.43.2")
    kapt(dependencyNotation = "com.google.dagger:hilt-android-compiler:2.43.2")

    // Timber
    implementation(dependencyNotation = "com.jakewharton.timber:timber:5.0.1")

    // Kotlin Coroutines
    implementation(dependencyNotation = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")

    // Kotlin Serialization
    implementation(dependencyNotation = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
}