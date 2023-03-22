plugins {
    `kotlin-dsl`
}
repositories {
    google()
    mavenCentral()
}

object PluginsVersions {
    const val GRADLE_ANDROID = "7.2.2"
    const val KOTLIN = "1.7.10"
    const val NAVIGATION = "2.5.1"
    const val HILT = "2.43.2"
}

dependencies {
    implementation("com.android.tools.build:gradle:${PluginsVersions.GRADLE_ANDROID}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.KOTLIN}")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:${PluginsVersions.NAVIGATION}")
    implementation("com.google.dagger:hilt-android-gradle-plugin:${PluginsVersions.HILT}")
}