// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(dependencyNotation = "com.android.tools.build:gradle:7.0.4")
        classpath(dependencyNotation = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath(dependencyNotation = "org.jetbrains.kotlin:kotlin-serialization:1.7.10")
        classpath(dependencyNotation = "com.google.dagger:hilt-android-gradle-plugin:2.43.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}