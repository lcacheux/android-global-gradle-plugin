// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    id("net.cacheux.android-global")
}

androidGlobal {
    android {
        defaultConfig {
            minSdk = 24
            targetSdk = 34

            versionCode = 42
            versionName = "0.42"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            consumerProguardFiles("consumer-rules.pro")
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        kotlinOptions {
            jvmTarget = "17"
        }
    }

    application {
        compileSdk = 34

        buildFeatures {
            buildConfig = true
        }
    }

    library {
        compileSdk = 34

        buildFeatures {
            buildConfig = true
        }
    }
}
