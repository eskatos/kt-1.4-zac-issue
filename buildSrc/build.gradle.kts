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

dependencies {
    implementation("com.android.tools.build:gradle:4.0.2")
}

println("buildSrc build script classpath kotlin compiler version " + KotlinCompilerVersion.VERSION)
