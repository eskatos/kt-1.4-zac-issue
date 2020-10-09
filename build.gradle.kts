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
}

slack {
    android {
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