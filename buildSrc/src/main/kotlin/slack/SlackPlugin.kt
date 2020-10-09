package slack

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

class SlackPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    target.extensions.create<SlackExtension>("slack")
  }
}