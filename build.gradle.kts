import Android.compileSdkVersion
import Android.minSdkVersion
import Android.targetSdkVersion

// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
//    ext {
        // App SDK versions.
//        compileSdkVersion = 28
//        minSdkVersion = 21
//        targetSdkVersion = 28

        // Dependency versions.
////        arch_lifecycle_version = '2.0.0'
//        constraint_layout_version = '1.1.3'
//        exoplayer_version = '2.9.1'
//        glide_version = '4.8.0'
//        gms_strict_version_matcher_version = '1.0.3'
//        gradle_version = '3.1.4'
//        gson_version = '2.8.5'
//        kotlin_version = '1.3.21'
//        androidx_version = '1.0.0'
//        androidx_app_compat_version = '1.0.2'
//        test_runner_version = '1.1.0'
//    }

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:3.5.0-alpha07")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.21")
//        classpath ("com.google.android.gms:strict-version-matcher-plugin:1.1.3")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
