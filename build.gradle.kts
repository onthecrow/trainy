// Top-level build file where you can add configuration options common to all sub-projects/modules.
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

buildscript {
    addRepos(repositories)
    dependencies {
        classpath(deps.google.services)
        classpath(deps.gradle.gradlePlugin)
        classpath(deps.kotlin.gradlePlugin)
        classpath(deps.gradleVersionsPlugin)
        classpath(deps.google.crashlyticsPlugin)
        classpath(deps.google.performancePlugin)
        classpath(deps.androidx.navigation.safeArgsPlugin)
    }
}

plugins {
    id("com.github.ben-manes.versions") version "0.28.0"
}

allprojects {
    addRepos(repositories)
}

tasks {

    @Suppress("UNUSED_VARIABLE")
    val clean by registering(Delete::class) {
        delete("build")
    }

    withType<DependencyUpdatesTask> {
        gradleReleaseChannel = "current"

        fun isNonStable(version: String): Boolean {
            val stableKeyword =
                listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
            val regex = "^[0-9,.v-]+(-r)?$".toRegex()
            val isStable = stableKeyword || regex.matches(version)
            return isStable.not()
        }

        rejectVersionIf {
            isNonStable(candidate.version) && !isNonStable(currentVersion)
        }
    }
}
