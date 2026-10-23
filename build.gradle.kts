plugins {
    id("java-library")
    id("xyz.jpenilla.run-paper") version "3.1.0"
}

repositories {
    mavenCentral()
    maven(url="https://repo.papermc.io/repository/maven-public/")
    maven(url="https://repo.codemc.io/repository/maven-releases/")
    maven(url="https://repo.codemc.io/repository/maven-snapshots/")
    maven(url="https://mvn.lib.co.nz/public")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.3.build.+")
    compileOnly("com.github.retrooper:packetevents-spigot:2.14.0")
    implementation("me.libraryaddict.disguises:libsdisguises:26.9.26")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(25)
}

tasks {
    runServer {
        // Configure the Minecraft version for our task.
        // This is the only required configuration besides applying the plugin.
        // Your plugin's jar (or shadowJar if present) will be used automatically.
        minecraftVersion("26.3")
        jvmArgs("-Xms2G", "-Xmx2G", "-Dcom.mojang.eula.agree=true")

        downloadPlugins {
            github("retrooper", "packetevents", "v2.14.0", "packetevents-spigot-2.14.0.jar")
            github("libraryaddict", "LibsDisguises", "v26.9.26", "LibsDisguises-26.9.26-Github.jar")
        }
    }

    processResources {
        val props = mapOf("version" to version, "description" to project.description)
        filesMatching("paper-plugin.yml") {
            expand(props)
        }
    }
}
