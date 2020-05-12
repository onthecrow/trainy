@file:Suppress("ClassName")

import org.gradle.api.artifacts.dsl.RepositoryHandler
import java.net.URI

object versions {
    const val kotlin = "1.3.72"
    const val compileSdkVersion = 29
    const val buildToolsVersion = "29.0.3"
    const val minSdkVersion = 23
    const val targetSdkVersion = 29
    const val junit = "4.13"
    const val junitAndroid = "1.1.1"
    const val espressoAndroid = "3.2.0"
    const val appCompat = "1.1.0"
    const val androidxCore = "1.2.0"
    const val constraintLayout = "1.1.3"
    const val gradlePlugin = "3.6.2"
    const val material = "1.1.0"
    const val coroutines = "1.3.5"
    const val googleServices = "4.3.3"
    const val firebaseAnalytics = "17.4.0"
    const val crashlyticsPlugin = "2.0.0"
    const val crashlytics = "17.0.0"
    const val performance = "19.0.7"
    const val performancePlugin = "1.3.1"
    const val vkSdk = "2.2.1"
    const val gradleVersionsPlugin = "0.28.0"
    const val facebook = "[4,5)"
    const val dagger = "2.27"
    const val crop = "2.2.4"
    const val glide = "4.11.0"
    const val navigation = "2.2.1"
    const val timber = "4.7.1"
    const val playAuth = "18.0.0"
    const val firebaseAuth = "19.3.1"
}

object deps {
    object gradle {
        const val gradlePlugin = "com.android.tools.build:gradle:${versions.gradlePlugin}"
    }

    object kotlin {
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}"
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${versions.kotlin}"
    }

    object test {
        const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        const val junit = "junit:junit:${versions.junit}"
        const val junitAndroid = "androidx.test.ext:junit:${versions.junitAndroid}"
        const val espressoAndroid =
            "androidx.test.espresso:espresso-core:${versions.espressoAndroid}"
    }

    object android {
        const val material = "com.google.android.material:material:${versions.material}"
    }

    object androidx {
        const val appCompat = "androidx.appcompat:appcompat:${versions.appCompat}"
        const val core = "androidx.core:core-ktx:${versions.androidxCore}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${versions.constraintLayout}"

        object navigation {
            const val runtime = "androidx.navigation:navigation-runtime-ktx:${versions.navigation}"
            const val fragment =
                "androidx.navigation:navigation-fragment-ktx:${versions.navigation}"
            const val ui = "androidx.navigation:navigation-ui-ktx:${versions.navigation}"
            const val safeArgsPlugin =
                "androidx.navigation:navigation-safe-args-gradle-plugin:${versions.navigation}"
        }
    }

    object coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${versions.coroutines}"
        const val android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${versions.coroutines}"
    }

    object google {
        const val services = "com.google.gms:google-services:${versions.googleServices}"
        const val firebaseAnalytics =
            "com.google.firebase:firebase-analytics:${versions.firebaseAnalytics}"
        const val crashlyticsPlugin =
            "com.google.firebase:firebase-crashlytics-gradle:${versions.crashlyticsPlugin}"
        const val crashlytics = "com.google.firebase:firebase-crashlytics:${versions.crashlytics}"
        const val performance = "com.google.firebase:firebase-perf:${versions.performance}"
        const val performancePlugin =
            "com.google.firebase:perf-plugin:${versions.performancePlugin}"
        const val playAuth = "com.google.android.gms:play-services-auth:${versions.playAuth}"
        const val firebaseAuth = "com.google.firebase:firebase-auth:${versions.firebaseAuth}"
    }

    object dagger {
        const val daggerCore = "com.google.dagger:dagger:${versions.dagger}"
        const val daggerAndroid = "com.google.dagger:dagger-android:${versions.dagger}"
        const val daggerAndroidSupport =
            "com.google.dagger:dagger-android-support:${versions.dagger}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${versions.dagger}"
        const val daggerProcessor = "com.google.dagger:dagger-android-processor:${versions.dagger}"
    }

    const val vkSdk = "com.vk:androidsdk:${versions.vkSdk}"
    const val gradleVersionsPlugin =
        "com.github.ben-manes:gradle-versions-plugin:${versions.gradleVersionsPlugin}"
    const val facebook = "com.facebook.android:facebook-android-sdk:${versions.facebook}"
    const val glide = "com.github.bumptech.glide:glide:${versions.glide}"
    const val crop = "com.github.yalantis:ucrop:${versions.crop}"
    const val timber = "com.jakewharton.timber:timber:${versions.timber}"
}

fun addRepos(repositoryHandler: RepositoryHandler) {
    repositoryHandler.google()
    repositoryHandler.jcenter()
    repositoryHandler.maven {
        url = URI.create("https://oss.sonatype.org/content/repositories/snapshots")
    }
    repositoryHandler.maven { url = URI.create("https://jitpack.io") }
}