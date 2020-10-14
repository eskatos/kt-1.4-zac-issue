pluginManagement {
  resolutionStrategy {
    eachPlugin {
      when (requested.id.id) {
        "com.squareup.wire" -> useModule("com.squareup.wire:wire-gradle-plugin:3.4.0")
      }
    }
  }
}