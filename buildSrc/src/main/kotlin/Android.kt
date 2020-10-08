interface BuildType

interface ProductFlavor

interface ApplicationVariant {
    val name: String
    val buildType: BuildType
    val productFlavors: List<ProductFlavor>
}
