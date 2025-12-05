plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.example.lap_time_tracker"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.lap_time_tracker"
        minSdk = 24
        targetSdk = 36
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
        }
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
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation("androidx.compose.material:material-icons-extended:1.7.8")

    // CameraX core
    implementation("androidx.camera:camera-core:1.5.2")
    implementation("androidx.camera:camera-camera2:1.5.2")
// CameraX PreviewView support
    implementation("androidx.camera:camera-view:1.5.2")
// CameraX Extensions (optional, if you want HDR, Night Mode etc.)
    implementation("androidx.camera:camera-extensions:1.5.2")
    implementation(libs.androidx.camera.lifecycle)
    implementation("io.coil-kt:coil-compose:2.7.0")
    implementation(libs.androidx.material3)



    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    implementation(libs.androidx.navigation.compose)
    // Lifecycle / ViewModel Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // Room
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)
    // DataStore
    implementation(libs.androidx.datastore.preferences)
    // WorkManager
    implementation(libs.androidx.work.runtime.ktx)
}