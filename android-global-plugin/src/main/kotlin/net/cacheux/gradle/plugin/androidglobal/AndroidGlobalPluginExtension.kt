package net.cacheux.gradle.plugin.androidglobal

import com.android.build.api.dsl.AndroidResources
import com.android.build.api.dsl.BuildFeatures
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.DefaultConfig
import com.android.build.api.dsl.ProductFlavor
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.TestedExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Action
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.plugin.extraProperties
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

typealias AndroidExtension = TestedExtension

abstract class AndroidGlobalCommonExtension: CommonExtension<BuildFeatures, BuildType, DefaultConfig, ProductFlavor, AndroidResources>

open class AndroidGlobalPluginExtension(private val project: Project) {
    companion object {
        const val ANDROID_GLOBAL = "androidGlobal"
        const val ANDROID_COMMON_GLOBAL = "androidCommonGlobal"
        const val APPLICATION_GLOBAL = "applicationGlobal"
        const val LIBRARY_GLOBAL = "libraryGlobal"
        const val KOTLIN_GLOBAL = "kotlinGlobal"
    }

    init {
        project.extensions.findByType(AndroidExtension::class.java)?.let { android ->
            project.getParentAction()?.execute(android)
        }

        project.tasks.withType(KotlinCompile::class.java).configureEach { compile ->
            project.getParentAction<KotlinJvmOptions>(KOTLIN_GLOBAL)?.let { compile.kotlinOptions(it) }
        }

        project.extensions.findByType(BaseAppModuleExtension::class.java)?.let { android ->
            project.getParentAction<BaseAppModuleExtension>(APPLICATION_GLOBAL)?.execute(android)
        }

        project.extensions.findByType(LibraryExtension::class.java)?.let {
            project.getParentAction<LibraryExtension>(LIBRARY_GLOBAL)?.execute(it)
        }
    }

    fun android(action: Action<AndroidExtension>) {
        project.extraProperties.set(ANDROID_GLOBAL, action)
    }

    fun kotlinOptions(action: Action<KotlinJvmOptions>) {
        project.extraProperties.set(KOTLIN_GLOBAL, action)
    }

    fun application(action: Action<BaseAppModuleExtension>) {
        project.extraProperties.set(APPLICATION_GLOBAL, action)
    }

    fun library(action: Action<LibraryExtension>) {
        project.extraProperties.set(LIBRARY_GLOBAL, action)
    }

    private fun Project.getParentAction(): Action<AndroidExtension>? {
        return if (extraProperties.has(ANDROID_GLOBAL)) {
            extraProperties.get(ANDROID_GLOBAL) as Action<AndroidExtension>
        } else {
            parent?.getParentAction()
        }
    }

    private fun <T> Project.getParentAction(label: String): Action<T>? {
        return if (extraProperties.has(label)) {
            extraProperties.get(label) as Action<T>
        } else {
            parent?.getParentAction(label)
        }
    }
}
