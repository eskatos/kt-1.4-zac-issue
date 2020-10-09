import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec
import slack.AndroidHandler
import slack.SlackAndroidAppExtension
import slack.SlackExtension

/*
 * This file exists because of a strange behavior in Gradle. If you want to access buildSrc code from the root project's
 * buildscript block, it cannot directly access elements that contain a package name. This is really weird, and
 * hopefully a bug.
 */

/**
 * Applies the given Android plugin [module].
 *
 * For example: `plugins { android("library") }`
 *
 * @param module simple name of the Android Gradle plugin module, for example "library", "application", etc...
 */
fun PluginDependenciesSpec.android(module: String): PluginDependencySpec =
  id("com.android.$module")

/**
 * Common entry point for configuring slack-specific bits of projects.
 *
 * ```
 * slack {
 *   android {
 *     library {
 *       // ...
 *     }
 *   }
 * }
 * ```
 */
fun Project.slack(body: SlackExtension.() -> Unit) {
  extensions.findByType<SlackExtension>()?.let(body) ?: error("Slack extension not found.")
}

/**
 * Common entry point for configuring slack-android-specific bits of projects.
 *
 * ```
 * slackAndroid {
 *   library {
 *     // ...
 *   }
 * }
 * ```
 */
fun Project.slackAndroid(action: Action<AndroidHandler>) {
  slack {
    android(action)
  }
}

/**
 * Common entry point for configuring slack-android-library-specific bits of projects.
 *
 * ```
 * androidApp {
 *   // ...
 * }
 * ```
 */
fun Project.slackAndroidApp(action: Action<SlackAndroidAppExtension>) {
  slack {
    android {
      app(action)
    }
  }
}
