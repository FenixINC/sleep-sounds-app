plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("org.jetbrains.kotlin.plugin.serialization")
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
    // Kotlin Coroutines
    implementation(dependencyNotation = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")

//    // Hilt
//    implementation(dependencyNotation = "com.google.dagger:hilt-android:2.40.5")
//    kapt(dependencyNotation = "com.google.dagger:hilt-android-compiler:2.40.5")
//
//    kapt(dependencyNotation = "org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.5.0")
//
//    // Kotlin Serialization
//    implementation(dependencyNotation = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
//
//    // Network
//    implementation(dependencyNotation = "io.ktor:ktor-client-core:2.0.1")
//    implementation(dependencyNotation = "io.ktor:ktor-client-android:2.0.1")
//    implementation(dependencyNotation = "io.ktor:ktor-client-content-negotiation:2.0.1")
//    implementation(dependencyNotation = "io.ktor:ktor-client-logging:2.0.1")
//    implementation(dependencyNotation = "io.ktor:ktor-serialization-kotlinx-json:2.0.1")

    // Test
    testImplementation(dependencyNotation = "junit:junit:4.13.2")
    testImplementation(dependencyNotation = "org.mockito.kotlin:mockito-kotlin:4.0.0")
    androidTestImplementation(dependencyNotation = "androidx.test.ext:junit:1.1.3")
    androidTestImplementation(dependencyNotation = "androidx.test.espresso:espresso-core:3.4.0")
}