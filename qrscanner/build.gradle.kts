plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id(BuildPlugins.KOTLIN_KAPT)
}

android {
    namespace = "co.deltapay.qrscanner"
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
    implementation(project(BuildModules.COMMON))

    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.COMPOSE_UI)
    implementation(Dependencies.COMPOSE_MATERIAL)
    implementation(Dependencies.COMPOSE_MATERIAL_ICON)
    implementation(Dependencies.COMPOSE_MATERIAL_3)
    implementation(Dependencies.COMPOSE_UI_TOOLING)
    implementation(Dependencies.COMPOSE_PREVIEW)
    implementation(Dependencies.PLACEHOLDER)
    kapt(AnnotationProcessorsDependencies.HILT)
    implementation(Dependencies.CAMERAX)
    implementation(Dependencies.CAMERAX_LIFECYCLE)
    implementation(Dependencies.CAMERA_VIEW)
    implementation(Dependencies.CAMERA_MLKIT_VISION)
    implementation(Dependencies.MLKIT_BARCODE_SCANNING)
    addTestsDependencies()
}
