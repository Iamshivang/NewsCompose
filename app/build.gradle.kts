plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "com.example.newscompose"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.newscompose"
        minSdk = 24
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
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    // Splash API
    implementation(libs.androidx.core.splashscreen)

    // Preferences dataStore
    implementation(libs.androidx.datastore.preferences)

    // hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Coroutine Lifecycle Scopes
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Activity KTX for viewModels()
    implementation(libs.androidx.activity.ktx)

    // Hilt Navigation Compose for ViewModel injection in composables
    implementation(libs.androidx.hilt.navigation.compose)

    // Navigation with Compose
    val nav_version = "2.9.0"
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)


    // retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Paging
    val paging_version = "3.3.6"
    implementation("androidx.paging:paging-runtime:$paging_version")
    // Jetpack Compose integration
    implementation("androidx.paging:paging-compose:3.3.6")

    // Lifecycle for viewmodel
    implementation(libs.androidx.lifecycle.viewmodel.ktx.v287)
    // Lifecycle runtime KTX extension
    implementation(libs.androidx.lifecycle.runtime.ktx.v287)

    implementation ("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Loading images using coil in Jetpack Compose
    implementation(libs.coil.compose)

    // room
    val room_version = "2.7.1"
    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:33.15.0"))
    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation(libs.firebase.auth)

    
    // material 3 for the navigation Drawer
    implementation(libs.material3)

}