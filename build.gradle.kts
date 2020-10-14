import org.jetbrains.kotlin.config.KotlinCompilerVersion

buildscript {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        jcenter()
    }
}

plugins {
    kotlin("jvm") version "1.4.10"
    id("slack.gradle")
    // Uncomment this to trigger the issue
//    id("com.android.application")
}

slack {
    android {
        println("slack.android is $this of type ${this::class}")
        app {
            permissionAllowlist {
                if (name == "externalRelease") {
                    setAllowlistFile(file("permissionsAllowlist.txt"))
                }
            }
        }
    }
}

repositories {
    mavenCentral()
    google()
    jcenter()
}

println("root build script classpath kotlin compiler version " + KotlinCompilerVersion.VERSION)
