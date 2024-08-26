// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false
    alias(libs.plugins.google.firebase.crashlytics) apply false
    alias(libs.plugins.dagger.hilt) apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.10"
}
buildscript {
    dependencies {
        // Add this line
        classpath("com.google.gms:google-services:4.3.15")
    }
}