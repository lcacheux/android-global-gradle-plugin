plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    id("net.cacheux.android-global")
}

android {
    namespace = "net.cacheux.androidglobal.test.app2"

    defaultConfig {
        applicationId = "net.cacheux.androidglobal.test.app2"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}