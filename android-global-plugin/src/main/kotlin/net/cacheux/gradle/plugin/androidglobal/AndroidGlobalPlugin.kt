package net.cacheux.gradle.plugin.androidglobal

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidGlobalPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.extensions.create("androidGlobal", AndroidGlobalPluginExtension::class.java)
    }
}
