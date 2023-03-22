// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.2.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${BuildDependenciesVersions.NAVIGATION}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${BuildDependenciesVersions.HILT}")
        classpath("com.google.gms:google-services:4.3.14")
        classpath( "com.google.firebase:firebase-appdistribution-gradle:${BuildDependenciesVersions.APP_DISTRIBUTION}")
        classpath( "com.google.firebase:firebase-crashlytics-gradle:${BuildDependenciesVersions.CRASHLYTICS_GRADLE}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}