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
    `java-gradle-plugin`
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
    compileOnly(gradleApi())
    implementation("com.android.tools.build:gradle:4.0.2")
}
