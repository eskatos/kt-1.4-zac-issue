plugins {
    id("my-plugin")
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
