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
    kotlin("jvm") version "1.3.72"
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
