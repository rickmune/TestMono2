plugins {
    id("common.android-library")
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = BuildDependenciesVersions.COMPOSE_COMPILER
    }
}

dependencies {

    api(fileTree("libs"))
    api(Dependencies.APPCOMPAT)
    api(Dependencies.CORE_KTX)
    api(Dependencies.TIMBER)
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.COMPOSE_UI)
    implementation(Dependencies.COMPOSE_MATERIAL)
    implementation(Dependencies.COMPOSE_MATERIAL_3)
    implementation(Dependencies.COMPOSE_MATERIAL_ICON)
    implementation(Dependencies.PERMISSION)
    implementation(Dependencies.CAMERAX)
    implementation(Dependencies.CAMERAX_LIFECYCLE)
    implementation(Dependencies.CAMERA_VIEW)
    implementation(Dependencies.PERMISSION)
    implementation(Dependencies.COMPOSE_UI_TOOLING)
    implementation(Dependencies.COMPOSE_PREVIEW)

    kapt(AnnotationProcessorsDependencies.HILT)
    implementation(Dependencies.COUNTRY_CODE_PICKER)
}