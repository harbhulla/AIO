val userHomeDir: String = System.getProperty("user.home");

plugins {
    java
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    // Adding the EternalClient API as a compileOnly dependency.
    compileOnly(files("${userHomeDir}/EternalClient/Data/EternalClient.jar"))

}

// Changing this will make it so that the jar file is named differently
val scriptFileName: String = "BROCKSALLINONE"

tasks {
    jar {
        archiveFileName.set("${scriptFileName}.jar")

        // Destination directory for the jar file, EternalClients local script directory.
        destinationDirectory.set(file("$userHomeDir/EternalClient/Scripts"))
    }
}