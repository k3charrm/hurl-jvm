/*
 * Copyright (C) 2020 Orange
 *
 * Hurl JVM (JVM Runner for https://hurl.dev)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    kotlin("jvm")
    application
}

val commonCliVersion = "1.4"
val sl4jJdkVersion = "1.7.29"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("commons-cli:commons-cli:$commonCliVersion")
    implementation("org.slf4j:slf4j-jdk14:$sl4jJdkVersion")
}

application {
    mainClassName = "com.orange.ccmd.hurl.fmt.MainKt"
}

tasks {
    jar {
        manifest {
            attributes["Main-Class"] = application.mainClassName
        }
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE

        // Include direct dependencies.
        configurations.compileClasspath.get().forEach { from(zipTree(it.absoluteFile)) }
        // Include core dependencies
        project(":hurl-core").configurations.compileClasspath.get().forEach { from(zipTree(it.absoluteFile)) }
    }
    processResources {
        filesMatching("application.properties") {
            val tokens = mapOf("version" to project.version)
            filter<ReplaceTokens>("tokens" to tokens)
        }
    }
}

// We override the default maven publication artifact "hurl-fmt"
publishing {
    publications {
        val maven = get("maven") as MavenPublication
        maven.artifactId = "hurlfmt"
    }
}