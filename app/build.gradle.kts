plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-parcelize")
    id ("androidx.navigation.safeargs")
    id ("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.stuart"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.stuart"
        minSdk = 21
        targetSdk = 34
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-firestore:24.9.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //Dagger hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    //Navigation component
    val nav_version = "2.5.2"
    implementation ("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    //loading button
    implementation("com.github.leandroborgesferreira:loading-button-android:2.3.0")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.13.0")
    //circular image
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    //viewpager2 indicator
    implementation("io.github.vejei.viewpagerindicator:viewpagerindicator:1.0.0-alpha")
    //stepView
    implementation ("com.github.shuhart:stepview:1.5.1")
    //Android Ktx
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.5")
    //Firebase
    implementation("com.google.firebase:firebase-auth:22.3.0")
}