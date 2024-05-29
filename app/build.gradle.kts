plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.inventarapp_v2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.inventarapp_v2"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
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

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    // Android Framework and Kotlin Extensions
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("androidx.activity:activity-ktx:1.4.0")
    implementation("org.jetbrains.kotlinx:ktoninx-coroutines-android:1.5.2")

    // Networking with Retrofit and OkHttp
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")

    // UI Components
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.1")

    // Testing Libraries
    testImplementation('junit:junit:4.13.2')
    androidTestImplementation('androidx.test:core:1.4.0')
    androidTestImplementation('androidx.test.ext:junit:1.1.3')
    androidTestImplementation('androidx.test:runner:1.4.0')
    androidTestImplementation('androidx.test:rules:1.4.0')
    androidTestImplementation('androidx.test.espresso:espresso-core:3.4.0')
    androidTestImplementation('androidx.test.espresso:espresso-contrib:3.4.0')
    androidTestImplementation('androidx.test.espresso:espresso-intents:3.4.0')

    // Mocking Frameworks
    testImplementation('org.mockito:mockito-core:4.0.0')
    androidTestImplementation('org.mockito:mockito-android:4.0.0')

    // MockWebServer for network tests
    testImplementation('com.squareup.okhttp3:mockwebserver:4.9.0')
}
