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
    `kotlin-dsl`
    id("com.squareup.wire")
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
    jcenter()
}

gradlePlugin {
    plugins.create("slackPlugin") {
        id = "slack.gradle"
        implementationClass = "slack.SlackPlugin"
    }
}

wire {
    kotlin {
        // If you uncomment this, it will fail to compile
        // If you keep it commented, observe that java sources are generated at
        // build/generated/source/wire anyway even though we've opted in to kotlin support, suggesting
        // calling this function in the DSL doesn't actually call Wire's extension
//        android = false
    }
    sourcePath {
        srcDir("src/main/proto")
        include("simple.proto")
    }
}

dependencies {
    implementation("com.android.tools.build:gradle:4.1.0")
    implementation("com.squareup.wire:wire-runtime:3.4.0")
}

println("buildSrc build script classpath kotlin compiler version " + KotlinCompilerVersion.VERSION)
