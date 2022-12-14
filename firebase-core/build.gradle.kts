plugins {
    id("com.android.library")
    kotlin("multiplatform")
}

android {
    multiplatformLibrary()
}

kotlin {
    android()

    fun configureNativeTarget(): org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget.() -> Unit = {
        val firebaseCoreFrameworks = listOf(
            "FirebaseCore",
            "GoogleUtilities"
        )

        val firebaseCoreDiagnosticsFrameworks = listOf(
            "FirebaseCoreDiagnostics",
            "GoogleDataTransport",
            "GoogleUtilities",
            "nanopb"
        )

        compilations.getByName("main") {
            cinterops.create("FirebaseCore") {
                configureCarthageFrameworks(rootDir, firebaseCoreFrameworks)

//                extraOpts = listOf("-compiler-option", "-DNS_FORMAT_ARGUMENT(A)=", "-verbose")
            }

            cinterops.create("FirebaseCoreDiagnostics") {
                configureCarthageFrameworks(rootDir, firebaseCoreDiagnosticsFrameworks)

//                extraOpts = listOf("-compiler-option", "-DNS_FORMAT_ARGUMENT(A)=", "-verbose")
            }
        }

        binaries.all {
            linkCarthageFrameworks(rootDir, firebaseCoreFrameworks)
            linkCarthageFrameworks(rootDir, firebaseCoreDiagnosticsFrameworks)
        }
    }

    ios(configure = configureNativeTarget())
    iosSimulatorArm64(configure = configureNativeTarget())
    macosArm64(configure = configureNativeTarget())
    macosX64(configure = configureNativeTarget())
    tvos(configure = configureNativeTarget())
    tvosSimulatorArm64(configure = configureNativeTarget())
//    watchos(configure = configureNativeTarget())
//    watchosSimulatorArm64(configure = configureNativeTarget())

    nativeSourceSets()

    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation("androidx.startup:startup-runtime:${Versions.Androidx.STARTUP}")
                implementation("com.google.firebase:firebase-common-ktx")
            }
        }
    }
}