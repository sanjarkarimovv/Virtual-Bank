plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.google.firebase.crashlytics)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.serialization")
    id ("kotlin-android")

}

android {
    namespace = "uz.androbeck.virtualbank"
    compileSdk = 34

    defaultConfig {
        applicationId = "uz.androbeck.virtualbank"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"http://195.158.16.140/mobile-bank/v1/\"")
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"http://195.158.16.140/mobile-bank/v1/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.database)
    implementation(libs.firebase.storage)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Crashlytics
    implementation(libs.firebase.crashlytics)

    //Navigation components
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //Dagger Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    //Coil
    implementation(libs.coil)

    //Retrofit, Okhttp, Gson-Converter
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.converter.gson)

    //View binding
    implementation(libs.view.binding)

    //Security
    implementation(libs.androidx.security.crypto.ktx)

    //MaskedEditText
    implementation(libs.input.redmadrobot)


    //room
    implementation(libs.androidx.room)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.kapt)

    //logging interceptor, serialization json,serialization converter
    implementation(libs.logging.interceptor)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.serialization.converter)

    //Chucker interceptor
    debugImplementation(libs.chucker.interceptor)
    releaseImplementation(libs.chucker.interceptor.no.op)

    // refresh layout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    //Biometrics
    implementation(libs.androidx.biometric)

    //Paging 3
    implementation("androidx.paging:paging-runtime:3.3.2")

    //MaskedEditText2
    implementation("io.github.vicmikhailau:MaskedEditText:5.0.2")

    //Lottie
    implementation("com.airbnb.android:lottie:3.4.0")

    //CameraX
    implementation("androidx.camera:camera-core:1.2.3")
    implementation("androidx.camera:camera-camera2:1.2.3")
    implementation("androidx.camera:camera-lifecycle:1.2.3")
    implementation("androidx.camera:camera-view:1.2.3")
    implementation("androidx.camera:camera-extensions:1.2.3")

    //Firebase-messaging
    implementation("com.google.firebase:firebase-messaging:23.2.0")

    // coil
    implementation("io.coil-kt:coil:2.4.0")

    // swiperefreshlayout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
}
