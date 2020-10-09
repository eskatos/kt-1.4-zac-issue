rootProject.name = "zac-issue"

pluginManagement {
  repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
    jcenter()
  }
  resolutionStrategy {
    eachPlugin {
      val requestedId = requested.id.id
      when {
        requestedId.startsWith("com.android") -> useModule("com.android.tools.build:gradle:4.0.2")
      }
    }
  }
}
