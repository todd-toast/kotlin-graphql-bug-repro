import com.expediagroup.graphql.plugin.gradle.tasks.GraphQLGenerateTestClientTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    alias(libs.plugins.expedia.graphql.plugin)
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

val createClient by tasks.creating(GraphQLGenerateTestClientTask::class) {
    packageName.set("com.example")
    val schema = file("$projectDir/schema.graphql")
    schemaFile.set(schema)
    queryFileDirectory.set(file("$projectDir/queries"))
}
