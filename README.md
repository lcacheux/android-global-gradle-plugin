# Android Global Gradle Plugin

This plugin provides a way to declare a common configuration for your Android modules in the root
build.gradle.kts file. Groovy build.gradle files are not supported.

This is still an experimental version as it was tested only with simple configurations. Most options
from the Android Gradle plugin extension should be available but some may not work.

## Usage

Import the plugin in your root build.gradle.kts file and in all build.gradle.kts you want to use it:

```kotlin
plugins {
    id("net.cacheux.android-global")
    ...
}
```

In the root file, you can add an `androidGlobal` block which will contain an `android` block with
all the common configuration for your Android modules:

```kotlin
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
}
```

Since application and library modules have different configuration options, some options that are
specific to these modules must be declared in `application` or `library` blocks:

```kotlin
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
```

As we can see, those two blocks contains the same configuration but this limitation is due to the
fact that `compileSdk` and `buildFeatures` belongs to different extension types in the Android
Gradle plugin.

Any option defined in a module will override the one defined in the root file.
