//plugins {
//    alias(libs.plugins.androidApplication)
//    alias(libs.plugins.jetbrainsKotlinAndroid)
//
//}
//
//android {
//    namespace = "com.example.vantage"
//    compileSdk = 36
//
//    defaultConfig {
//        applicationId = "com.example.vantage"
//        minSdk = 24
//        targetSdk = 36
//        versionCode = 1
//        versionName = "1.0"
//
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//    }
//
//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
//    kotlinOptions {
//        jvmTarget = "1.8"
//    }
//}
//
//dependencies {
//
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
//    implementation(libs.androidx.activity)
//    implementation(libs.androidx.constraintlayout)
//    implementation(libs.play.services.maps)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//    implementation(libs.play.services.maps)
//    implementation("org.osmdroid:osmdroid-android:6.1.18")
//

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.vantage"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.vantage"
        minSdk = 24
        targetSdk = 36 // Updated this to match compileSdk for better compatibility
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // YOUR FREE MAP: OpenStreetMap
    // UPGRADED osmdroid to 6.1.20 to match the routing library's requirements
    implementation("org.osmdroid:osmdroid-android:6.1.20")
    implementation("com.github.MKergall:osmbonuspack:6.9.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}