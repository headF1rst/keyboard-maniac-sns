import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.sonarqube") version "4.4.1.3373"
	id("org.springframework.boot") version "3.2.0"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.21"
	kotlin("plugin.jpa") version "1.9.21"
	kotlin("plugin.spring") version "1.9.21"
}

allprojects {
	group = "com.springles"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}
}

subprojects {
	apply(plugin = "idea")
	apply(plugin = "kotlin")
	apply(plugin = "kotlin-spring")
	apply(plugin = "kotlin-jpa")
	apply(plugin = "kotlin-kapt")
	apply(plugin = "kotlin-noarg")
	apply(plugin = "kotlin-allopen")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")

	dependencies {
		implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.21")
		implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.21")
		annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
		testImplementation("io.mockk:mockk:1.13.7")
		testImplementation("com.ninja-squad:springmockk:4.0.2")
		testImplementation("io.kotest:kotest-runner-junit5:5.6.0")
		testImplementation("io.kotest:kotest-assertions-core:5.6.0")
		testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
	}

	java {
		toolchain {
			languageVersion.set(JavaLanguageVersion.of(17))
		}
	}

	configurations {
		compileOnly {
			extendsFrom(configurations.annotationProcessor.get())
		}
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs += "-Xjsr305=strict"
			jvmTarget = "17"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

}

sonar {
	properties {
		property("sonar.projectKey", "keyboard-maniac-sns")
		property("sonar.organization", "maniac")
		property("sonar.host.url", "https://sonarcloud.io")
	}
}
