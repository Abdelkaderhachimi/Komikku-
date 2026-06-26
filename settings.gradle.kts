@file:Suppress("ktlint:standard:kdoc")

pluginManagement {
    includeBuild("gradle/build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://www.jitpack.io")
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("kei") {
            from(files("gradle/kei.versions.toml"))
        }
    }
    @Suppress("UnstableApiUsage")
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
        maven(url = "https://www.jitpack.io")
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Keiyoushi"

/**
 * Add or remove modules to load as needed for local development here.
 */
loadAllIndividualExtensions()
// loadIndividualExtension("all", "mangadex")

/**
 * ===================================== COMMON CONFIGURATION ======================================
 */
include(":core")

// Load all modules under /lib
File(rootDir, "lib").listFiles()?.filter { it.isDirectory }?.forEach { 
    include("lib:${it.name}") 
}

// Load all modules under /lib-multisrc
File(rootDir, "lib-multisrc").listFiles()?.filter { it.isDirectory }?.forEach { 
    include("lib:${it.name}")
}

/**
 * ======================================== HELPER FUNCTION ========================================
 */
 {
    File(rootDir, "src").listFiles()?.filter { it.isDirectory }?.forEach { dir ->
    dir.listFiles()?.filter { it.isDirectory }?.forEach { subdir ->
        include("src:${dir.name}:${subdir.name}")
    }
}
        }
    }
}
fun loadIndividualExtension(lang: String, name: String) {
    include("src:$lang:$name")
}
