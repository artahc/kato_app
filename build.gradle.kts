// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    kotlin("kapt") version "1.9.0"
}

buildscript {
    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        val nav_version = "2.7.6"

        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}