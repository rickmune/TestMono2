import BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER

plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
    id(BuildPlugins.KOTLIN_PARCELIZE)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.NAVIGATION)
    id(BuildPlugins.HILT)
    id(BuildPlugins.GMS)
    id(BuildPlugins.APP_DISTRIBUTION)
    id(BuildPlugins.CRASHLYTICS)
}

android {
    signingConfigs {
        create("release") {
            storeFile =
                file("./release.keystore")
            storePassword = "Delt@p@y"
            keyAlias = "Deltapay"
            keyPassword = "Delt@p@y"
        }
    }
    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION
    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION

        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME

        testInstrumentationRunner = TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        getByName("debug") {
            firebaseAppDistribution {
                releaseNotes = "New Release"
                testers = "darius@deltapay.co, dickson@deltapay.co, joshua@deltapay.co, dennis@deltapay.co, \n" +
                        "victortayo@gmail.com, mike@deltapay.co"
            }
        }
        getByName("release") {
            isMinifyEnabled = false
            signingConfig  = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += "environment"
    productFlavors {
        create("production") {
            dimension = "environment"
            versionNameSuffix = ""
        }
        create("staging") {
            dimension = "environment"
            versionNameSuffix = "-staging"
        }
        create("dev") {
            dimension = "environment"
            versionNameSuffix = "-dev"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }


}

dependencies {
    implementation(project(BuildModules.FEATURE_LIST))
    implementation(project(BuildModules.HOME))

    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.HILT)
    implementation(Dependencies.ROOM_KTX)
    implementation(Dependencies.ROOM_RUNTIME)
    implementation(Dependencies.ANDROIDX_SPLASH_SCREEN)
    implementation(platform(Dependencies.FIREBASE_BOM))
    implementation(Dependencies.FIREBASE_ANALYTICS)
    implementation(Dependencies.FIREBASE_CRASHLYTICS)
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
    kapt(AnnotationProcessorsDependencies.HILT)
    kapt(AnnotationProcessorsDependencies.ROOM)


    addTestsDependencies()
}