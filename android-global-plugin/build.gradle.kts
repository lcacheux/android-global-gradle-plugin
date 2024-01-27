plugins {
    kotlin("jvm") version "1.8.10"
    id("java-gradle-plugin")
    id("com.gradle.plugin-publish") version "1.2.1"
}

group = "net.cacheux.gradle"
version = "0.1.0"

gradlePlugin {
    website = "https://github.com/lcacheux/android-global-gradle-plugin"
    vcsUrl = "https://github.com/lcacheux/android-global-gradle-plugin.git"

        plugins {
        create("net.cacheux.android-global") {
            id = "net.cacheux.android-global"
            implementationClass = "net.cacheux.gradle.plugin.androidglobal.AndroidGlobalPlugin"
            displayName = "Android global configuration"
            description = "Allow to define Android Gradle plugin configuration common to all modules"
            tags = listOf("android", "kotlin", "configuration")
        }
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("gradle-plugin-api"))
    implementation(gradleApi())
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
    compileOnly("com.android.tools.build:gradle:8.2.0")
}
