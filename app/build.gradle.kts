plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.hilt)
    id("kotlin-kapt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.mohamedelattar.qrscanner"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mohamedelattar.qrscanner"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    kapt {
        correctErrorTypes = true
        useBuildCache = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(project(":domain"))
    implementation(project(":data"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)

    // To use Kotlin annotation processing tool (kapt)
    kapt(libs.androidx.room.compiler)


    // Lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Navigation
    implementation(libs.androidx.navigation.compose)
    implementation("androidx.compose.material3:material3-window-size-class:1.3.1")

    implementation(libs.coil.compose.v304)
    implementation(libs.coil.network.okhttp)

    // Hilt
    implementation(libs.google.hilt.android)
    kapt(libs.hilt.compiler)
    kapt(libs.google.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // Gson
    implementation(libs.gson)
    implementation(libs.converter.gson)

    //Encoding QR Code Library
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation("com.google.zxing:core:3.4.1")


    // CameraX
    implementation("androidx.camera:camera-camera2:1.0.2")
    implementation("androidx.camera:camera-lifecycle:1.0.2")
    implementation("androidx.camera:camera-view:1.0.0-alpha31")

    implementation("com.google.accompanist:accompanist-permissions:0.28.0")


}