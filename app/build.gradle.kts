plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf")
    id("androidx.navigation.safeargs")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(versions.compileSdkVersion)
    buildToolsVersion(versions.buildToolsVersion)

    defaultConfig {
        applicationId = "com.onthecrow.trainy"
        minSdkVersion(versions.minSdkVersion)
        targetSdkVersion(versions.targetSdkVersion)
        versionCode = 1
        versionName = "0.1"
        testInstrumentationRunner = deps.test.testInstrumentationRunner
    }

    signingConfigs {
        create("release") {
            storeFile = keystore.getStoreFile(projectDir)
            storePassword = keystore.storePassword
            keyAlias = keystore.keyAlias
            keyPassword = keystore.keyPassword
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    dataBinding {
        isEnabled = true
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(deps.kotlin.stdLib)
    implementation(deps.androidx.appCompat)
    implementation(deps.androidx.core)
    implementation(deps.androidx.constraintLayout)
    implementation(deps.androidx.navigation.fragment)
    implementation(deps.androidx.navigation.runtime)
    implementation(deps.androidx.navigation.ui)
    implementation(deps.android.material)
    implementation(deps.google.firebaseAnalytics)
    implementation(deps.google.crashlytics)
    implementation(deps.google.performance)
    implementation(deps.google.playAuth)
    implementation(deps.google.firebaseAuth)
    implementation(deps.coroutines.core)
    implementation(deps.coroutines.android)
    implementation(deps.dagger.daggerCore)
    implementation(deps.dagger.daggerAndroid)
    implementation(deps.dagger.daggerAndroidSupport)
    implementation(deps.crop)
    implementation(deps.glide)
    implementation(deps.timber)
    implementation(deps.vkSdk)
    implementation(deps.facebook)

    testImplementation(deps.test.junit)

    androidTestImplementation(deps.test.junitAndroid)
    androidTestImplementation(deps.test.espressoAndroid)

    kapt(deps.dagger.daggerCompiler)
    kapt(deps.dagger.daggerProcessor)
}
