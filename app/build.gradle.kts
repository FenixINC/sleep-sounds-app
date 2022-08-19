plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.example.sleepsoundsapp"
        minSdk = 28
        targetSdk = 32
        versionCode = 1
        versionName = "0.0.1"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0-rc01"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(path = ":core-navigation"))
    implementation(project(path = ":core-viewmodel"))

    implementation(project(path = ":feature-splash"))
    implementation(project(path = ":feature-sleep"))

    // AndroidX
    implementation(dependencyNotation = "androidx.core:core-ktx:1.8.0")
    implementation(dependencyNotation = "androidx.appcompat:appcompat:1.5.0")
    implementation(dependencyNotation = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.0-alpha01")

    // Material
    implementation(dependencyNotation = "com.google.android.material:material:1.6.1")

    // Compose
    implementation(dependencyNotation = "androidx.compose.compiler:compiler:1.3.0-rc01")
    implementation(dependencyNotation = "androidx.compose.runtime:runtime:1.3.0-alpha02")
    implementation(dependencyNotation = "androidx.compose.ui:ui:1.3.0-alpha02")
    implementation(dependencyNotation = "androidx.compose.ui:ui-tooling-preview:1.3.0-alpha02")
    implementation(dependencyNotation = "androidx.constraintlayout:constraintlayout-compose:1.0.1")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation(dependencyNotation = "androidx.compose.foundation:foundation:1.3.0-alpha02")
    // Material Design, Icons
    implementation(dependencyNotation = "androidx.compose.material:material:1.2.1")
    implementation(dependencyNotation = "androidx.compose.material:material-icons-core:1.2.1")
    implementation(dependencyNotation = "androidx.compose.material:material-icons-extended:1.2.1")
    // Integration with activities
    implementation(dependencyNotation = "androidx.activity:activity-compose:1.5.1")
    // Integration with ViewModels
    implementation(dependencyNotation = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
//    // Navigation Hilt
    implementation(dependencyNotation = "androidx.hilt:hilt-navigation-compose:1.0.0")
//    // Navigation Compose
    implementation(dependencyNotation = "androidx.navigation:navigation-compose:2.5.1")
    // Accompanist system ui controller
    implementation(dependencyNotation = "com.google.accompanist:accompanist-systemuicontroller:0.24.3-alpha")
    // Accompanist Navigation Compose Animations
    implementation(dependencyNotation = "com.google.accompanist:accompanist-navigation-animation:0.24.3-alpha")

//    // Navigation Voyager
//    implementation(dependencyNotation = "cafe.adriel.voyager:voyager-navigator:1.0.0-rc02")
//    implementation(dependencyNotation = "cafe.adriel.voyager:voyager-transitions:1.0.0-rc02")
//    implementation(dependencyNotation = "cafe.adriel.voyager:voyager-tab-navigator:1.0.0-rc02")
//    implementation(dependencyNotation = "cafe.adriel.voyager:voyager-transitions:1.0.0-rc02")
//    implementation(dependencyNotation = "cafe.adriel.voyager:voyager-androidx:1.0.0-rc02")
//    implementation(dependencyNotation = "cafe.adriel.voyager:voyager-koin:1.0.0-rc02")
//    implementation(dependencyNotation = "cafe.adriel.voyager:voyager-hilt:1.0.0-rc02")

    // Kotlin Coroutines
    implementation(dependencyNotation = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")

//    // Kotlin Serialization
//    implementation(dependencyNotation = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
//
//    // Network
//    implementation(dependencyNotation = "io.ktor:ktor-client-core:2.0.1")
//    implementation(dependencyNotation = "io.ktor:ktor-client-android:2.0.1")
//    implementation(dependencyNotation = "io.ktor:ktor-client-content-negotiation:2.0.1")
//    implementation(dependencyNotation = "io.ktor:ktor-client-logging:2.0.1")
//    implementation(dependencyNotation = "io.ktor:ktor-serialization-kotlinx-json:2.0.1")

    // Dagger
    implementation(dependencyNotation = "com.google.dagger:dagger:2.43.2")
    kapt(dependencyNotation = "com.google.dagger:dagger-compiler:2.43.2")

    // Hilt
    implementation(dependencyNotation = "com.google.dagger:hilt-android:2.43.2")
    kapt(dependencyNotation = "com.google.dagger:hilt-android-compiler:2.43.2")

    // Timber
    implementation(dependencyNotation = "com.jakewharton.timber:timber:5.0.1")

    // Test
    testImplementation(dependencyNotation = "junit:junit:4.13.2")
    testImplementation(dependencyNotation = "org.mockito.kotlin:mockito-kotlin:4.0.0")
    androidTestImplementation(dependencyNotation = "androidx.test.ext:junit:1.1.3")
    androidTestImplementation(dependencyNotation = "androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation(dependencyNotation = "androidx.compose.ui:ui-test-junit4:1.3.0-alpha02")
    debugImplementation(dependencyNotation = "androidx.compose.ui:ui-tooling:1.3.0-alpha02")
}