plugins {
    alias(libs.plugins.kotlinJvm)
    id("java-gradle-plugin")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("gradle-plugin-api"))
    implementation(gradleApi())
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.gradle)

    testImplementation(libs.junit)
    testImplementation(libs.build.gradle)
    testImplementation(gradleTestKit())
}
