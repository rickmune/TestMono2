plugins {
    id("common.android-library")
    id(BuildPlugins.NAVIGATION)
    id(BuildPlugins.HILT)
    id("org.jetbrains.kotlin.android")
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = BuildDependenciesVersions.COMPOSE_COMPILER
    }

    flavorDimensions += "environment"
    productFlavors {
        create("production") {
        }
        create("staging") {
        }
        create("dev") {
        }
    }
}

dependencies {

    implementation(project(BuildModules.CORE))
    implementation(project(BuildModules.LIB_OTP))
    implementation(project(BuildModules.QR_SCANNER))

    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.COIL)
    implementation(Dependencies.COMPOSE_LIVEDATA)
    implementation(Dependencies.COMPOSE_UI)
    implementation(Dependencies.COMPOSE_MATERIAL)
    implementation(Dependencies.COMPOSE_MATERIAL_ICON)
    implementation(Dependencies.COMPOSE_MATERIAL_3)
    implementation(Dependencies.COMPOSE_UI_TOOLING)
    implementation(Dependencies.COMPOSE_PREVIEW)
    implementation(Dependencies.COMPOSE_CONSTRAIN_LAYOUT)
    implementation(Dependencies.ACTIVITY_COMPOSE)

    implementation(Dependencies.SWIPE_REFRESH)
    implementation(Dependencies.PLACEHOLDER)
    implementation(Dependencies.SKYDOVES)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    //implementation(project(mapOf("path" to ":widget")))
    kapt(AnnotationProcessorsDependencies.HILT)
    implementation(Dependencies.CAMERAX)
    implementation(Dependencies.CAMERAX_LIFECYCLE)
    implementation(Dependencies.CAMERA_VIEW)
    implementation(Dependencies.PERMISSION)
    implementation(Dependencies.COUNTRY_CODE_PICKER)
    implementation(Dependencies.PLAY_SERVICE_ML_TEXT_REC)
    implementation(Dependencies.NAV_COMPOSE)
    implementation(Dependencies.PAGER)
    implementation(Dependencies.COMPOSE_VIEWMODEL)
    implementation(Dependencies.HILT_NAVIGATION)
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation(Dependencies.MOSHI_KTX)
    implementation("com.squareup.moshi:moshi-adapters:1.14.0")
    implementation("com.github.withmono:mono-connect-android:v1.1.0")

    addTestsDependencies()
}
