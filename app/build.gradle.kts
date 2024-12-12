// Instruções de como conseguir sua chave de API através desse link: https://developers.google.com/maps/documentation/maps-static/cloud-setup
private val mapsApiKey = "Insira sua chave de API do Google Maps aqui"

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kapt)
    alias(libs.plugins.serialization)
}

android {
    namespace = "br.com.passenger"
    compileSdk = 35

    defaultConfig {
        applicationId = "br.com.passenger"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://xd5zl5kk2yltomvw5fb37y3bm40vsyrx.lambda-url.sa-east-1.on.aws/\"",
            )
            buildConfigField(
                "String",
                "MAPS_API_KEY",
                "\"$mapsApiKey\"",
            )
            buildConfigField(
                "String",
                "MAPS_API_URL",
                "\"https://maps.googleapis.com/maps/api/\"",
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://xd5zl5kk2yltomvw5fb37y3bm40vsyrx.lambda-url.sa-east-1.on.aws/\"",
            )
            buildConfigField(
                "String",
                "MAPS_API_KEY",
                "\"$mapsApiKey\"",
            )
            buildConfigField(
                "String",
                "MAPS_API_URL",
                "\"https://maps.googleapis.com/maps/api/\"",
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
        buildConfig = true
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

    implementation(libs.bundles.retrofit)
    implementation(libs.coroutines)
    implementation(libs.dagger.hilt)
    kapt(libs.kapt)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.coil)

    testImplementation(libs.bundles.test)
}

kapt {
    correctErrorTypes = true
}
