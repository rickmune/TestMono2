plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id(BuildPlugins.KOTLIN_KAPT)
}

android {
    namespace = "co.deltapay.otp"
    compileSdk = BuildAndroidConfig.TARGET_SDK_VERSION

    defaultConfig {
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION

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

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = BuildDependenciesVersions.COMPOSE_COMPILER
    }
}

dependencies {
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
    implementation(Dependencies.PLACEHOLDER)
    implementation(Dependencies.SKYDOVES)
    implementation(Dependencies.PERMISSION)
    implementation(Dependencies.PLAY_SERVICE_AUTH_API_PHONE)
    implementation(Dependencies.ACTIVITY_COMPOSE)

    addTestsDependencies()
}
