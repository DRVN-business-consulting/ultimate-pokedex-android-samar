plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "dev.samar.ultimatepokedex"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.samar.ultimatepokedex"
        minSdk = 26
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
}

dependencies {
    implementation ("net.zetetic:sqlcipher-android:4.6.0@aar")
    implementation ("androidx.sqlite:sqlite:2.4.0")
    implementation ("androidx.fragment:fragment:1.8.3")
    implementation ("com.google.android.material:material:1.12.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("com.google.android.gms:play-services-maps:19.0.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:4.10.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.0")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.8.0")
    implementation ("androidx.core:core-ktx:1.10.0")
    implementation ("androidx.fragment:fragment-ktx:1.6.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation (libs.appcompat)
            implementation (libs.material)
            implementation (libs.activity)
            implementation(libs.constraintlayout)
            testImplementation (libs.junit)
            androidTestImplementation (libs.ext.junit)
            androidTestImplementation (libs.espresso.core)
}