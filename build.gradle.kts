/*
 * This file was generated by the Gradle 'init' task.
 *
 * This is a general purpose Gradle build.
 * Learn how to create Gradle builds at https://guides.gradle.org/creating-new-gradle-builds/
 */
repositories {
    jcenter()
    mavenCentral()
}

plugins {
    application
    kotlin("jvm") version "1.3.10"
}

dependencies {
    compile(kotlin("stdlib"))
    compile(kotlin("reflect"))
    testCompile(kotlin("test-common"))
    testCompile(kotlin("test-annotations-common"))
    testCompile(kotlin("test-junit"))
}
