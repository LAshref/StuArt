buildscript {
    dependencies {
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.44")
        classpath ("com.google.gms:google-services:4.4.0")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
}