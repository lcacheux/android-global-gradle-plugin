package net.cacheux.androidglobal

import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

class BuildTest {
    @Test
    fun test() {
        val result = GradleRunner.create()
            .withProjectDir(File(".."))
            .withArguments("assemble")
            .withPluginClasspath()
            .forwardOutput()
            .build()

        assertEquals(TaskOutcome.SUCCESS, result.task(":app:assemble")?.outcome)
        assertEquals(TaskOutcome.SUCCESS, result.task(":app2:assemble")?.outcome)
        assertEquals(TaskOutcome.SUCCESS, result.task(":lib:assemble")?.outcome)

        with(buildConfig("app")) {
            assert(exists())
            assert(readText().contains("VERSION_NAME = \"1.0\""))
        }

        with(buildConfig("app2")) {
            assert(exists())
            assert(readText().contains("VERSION_NAME = \"0.42\""))
        }

        with(buildConfig("lib")) {
            assert(exists())
            assert(readText().contains("BUILD_TYPE = \"debug\""))
        }
    }

    private fun buildConfig(project: String)
        = File("../$project/build/generated/source/buildConfig/debug/net/cacheux/androidglobal/test/$project/BuildConfig.java")
}