package slack

import com.android.build.gradle.api.ApplicationVariant
import com.android.builder.model.BuildType
import com.android.builder.model.ProductFlavor
import org.gradle.api.Action
import org.gradle.api.model.ObjectFactory
import org.gradle.kotlin.dsl.newInstance
import java.io.File
import javax.inject.Inject

@DslMarker
annotation class SlackExtensionMarker

@SlackExtensionMarker
abstract class SlackExtension @Inject constructor(objects: ObjectFactory) {

    internal val androidHandler = objects.newInstance<AndroidHandler>()

    fun android(action: Action<AndroidHandler>) {
        action.execute(androidHandler)
    }
}

@SlackExtensionMarker
@Suppress("UnnecessaryAbstractClass")
abstract class AndroidHandler @Inject constructor(objects: ObjectFactory) {
    internal val appHandler = objects.newInstance<SlackAndroidAppExtension>()

    fun app(action: Action<SlackAndroidAppExtension>) {
        action.execute(appHandler)
    }
}

@SlackExtensionMarker
abstract class SlackAndroidAppExtension {
    internal var allowlistAction: Action<PermissionAllowlistConfigurer>? = null

    /**
     * Configures a permissions allowlist on a per-variant basis with a VariantFilter-esque API.
     *
     * Example:
     * ```
     * slack {
     *   permissionAllowlist {
     *     if (buildType.name == "release") {
     *       setAllowlistFile(file('path/to/allowlist.txt'))
     *     }
     *   }
     * }
     * ```
     */
    fun permissionAllowlist(factory: Action<PermissionAllowlistConfigurer>) {
        allowlistAction = factory
    }

    interface PermissionAllowlistConfigurer : VariantConfiguration {
        /**
         * Sets a file containing a newline-delimited allowlist of permissions. If set, merged manifest permissions
         * for this variant will have their permissions checked against the allowlist defined in [file].
         */
        fun setAllowlistFile(file: File)
    }

    internal class DefaultPermissionAllowlistConfigurer(
            variant: ApplicationVariant
    ) : PermissionAllowlistConfigurer, VariantConfiguration by DefaultVariantConfiguration(variant) {
        internal var file: File? = null

        override fun setAllowlistFile(file: File) {
            this.file = file
        }
    }
}

/** Base interface for interfaces that configure on a per-variant basis and need to expose variant info. */
interface VariantConfiguration {
    /**
     * Returns the Build Type.
     */
    val buildType: BuildType

    /**
     * Returns the list of flavors, or an empty list.
     */
    val flavors: List<ProductFlavor>

    /**
     * Returns the unique variant name.
     */
    val name: String
}

private class DefaultVariantConfiguration(private val variant: ApplicationVariant) : VariantConfiguration {
    override val buildType: BuildType get() = variant.buildType
    override val flavors: List<ProductFlavor> get() = variant.productFlavors
    override val name: String get() = variant.name
}
