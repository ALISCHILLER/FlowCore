plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlinx-serialization")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.msa.core"
    compileSdk = 35

    defaultConfig {
        minSdk = 27

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true // فعال کردن BuildConfig
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.compose.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
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
    implementation (libs.material3)

    //di koin
    implementation(dependency.koin.androidx.compose)
    implementation(dependency.koin.test)
    testImplementation(dependency.koin.android.test)


    //  exoplayer
    implementation ("androidx.media3:media3-exoplayer:1.6.1")
    implementation ("androidx.media3:media3-ui:1.6.1")
    implementation ("androidx.media3:media3-common:1.6.1")
    //network ktor
    implementation(platform("io.ktor:ktor-bom:3.1.2"))
    implementation("io.ktor:ktor-client-android")
    implementation("io.ktor:ktor-client-serialization")
    implementation("io.ktor:ktor-client-logging")
    implementation("io.ktor:ktor-client-content-negotiation")
    implementation("io.ktor:ktor-serialization-kotlinx-json")
    implementation("io.ktor:ktor-client-okhttp:2.3.4")

    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    // log  timber
    implementation ("com.jakewharton.timber:timber:5.0.1")

    // image loader coil
    implementation("io.coil-kt.coil3:coil-compose:3.1.0")

    //DB Room
    implementation("androidx.room:room-runtime:2.7.1")
    ksp("androidx.room:room-compiler:2.7.1")
    implementation("androidx.room:room-ktx:2.7.1")

    // CameraX
    implementation ("androidx.camera:camera-core:1.4.0-alpha02")
    implementation ("androidx.camera:camera-lifecycle:1.4.0-alpha02")
    implementation ("androidx.camera:camera-view:1.4.0-alpha02")
    // Permissions
    implementation ("com.google.accompanist:accompanist-permissions:0.30.1")



    implementation("androidx.security:security-crypto:1.0.0")
    implementation("androidx.security:security-state:1.0.0")
    implementation("com.google.code.gson:gson:2.13.1")
}