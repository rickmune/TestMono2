/**
 * Configuration of all gradle build plugins
 */
object BuildPlugins {
    const val ANDROID_APPLICATION = "com.android.application"
    const val KOTLIN_ANDROID = "kotlin-android"
    const val KOTLIN_PARCELIZE = "kotlin-parcelize"
    const val KOTLIN_KAPT = "kotlin-kapt"
    const val HILT = "dagger.hilt.android.plugin"
    const val NAVIGATION = "androidx.navigation.safeargs"
    const val GMS =  "com.google.gms.google-services"
    const val APP_DISTRIBUTION = "com.google.firebase.appdistribution"
    const val CRASHLYTICS = "com.google.firebase.crashlytics"
}