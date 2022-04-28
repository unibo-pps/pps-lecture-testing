plugins {
    id("java")
    id("scala")
    id("org.scoverage") version "4.0.1"
    //id("com.github.maiflai.scalatest") version "0.21"
    //id("com.commercehub.cucumber-jvm") version "0.11"
    //id("ea")
}

repositories {
    mavenCentral()
    jcenter()
}

val scalaVersion = "3.1.2"
val scalaVer = "3" // "2.12"
val junitPlatformVersion = "1.8.2"

dependencies {
    implementation("org.scala-lang:scala3-library_$scalaVer:$scalaVersion")

    testImplementation("junit:junit:4.12")

    testImplementation("org.scalatest:scalatest_$scalaVer:3.2.11")
    testRuntimeOnly("co.helmethair:scalatest-junit-runner:0.1.10")
    testRuntimeOnly("org.pegdown:pegdown:1.4.2")
    //testImplementation("org.devzendo:scalacheck-contrib_2.11:1.0.0")
    // scoverage("org.scoverage:scalac-scoverage-plugin_2.12:1.3.1", "org.scoverage:scalac-scoverage-runtime_2.12:1.3.1")

    // N.B.: ScalaMock not yet ported to Scala 3
    // testImplementation("org.scalamock:scalamock-scalatest-support_$scalaVer:3.5.0")

    testImplementation("io.cucumber:cucumber-scala_$scalaVer:8.2.6")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.3.2") // needed for runner (cucumber.api.junit.Cucumber)
    //testImplementation("io.cucumber:cucumber-java:2.4.0")

    //testImplementation("org.seleniumhq.selenium:selenium-java:2.35.0")
    testImplementation("org.scalatestplus:selenium-4-1_$scalaVer:3.2.11.0")
    testImplementation("org.scalatestplus:scalacheck-1-15_$scalaVer:3.2.10.0")

    testImplementation("com.tngtech.archunit:archunit:0.18.0")

    // testImplementation("junit:junit:4.12") // Junit 4
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitPlatformVersion")
    testRuntimeOnly   ("org.junit.jupiter:junit-jupiter-engine:$junitPlatformVersion")
    testImplementation("org.junit.platform:junit-platform-launcher:$junitPlatformVersion") // for org.junit.platform stuff (cf. SomeJUnit5Runner)
    testRuntimeOnly   ("org.junit.vintage:junit-vintage-engine:$junitPlatformVersion")
    testImplementation("org.junit.platform:junit-platform-suite-api:$junitPlatformVersion")
    testImplementation("org.junit.platform:junit-platform-suite-engine:$junitPlatformVersion")
    testImplementation("org.junit.platform:junit-platform-engine:$junitPlatformVersion")

    testRuntimeOnly("org.slf4j:slf4j-log4j12:1.7.26")
}

/*
tasks.withType<ScalaCompile> {
    sourceCompatibility = JavaVersion.VERSION_1_8.toString()
    targetCompatibility = JavaVersion.VERSION_1_8.toString()
}

configurations {
    register("cucumberRuntime") {
        extendsFrom(configurations["testRuntime"])
    }
}
 */

//test.dependsOn cuke // needed if ScalaTest plugin is activated

tasks {
    register<JavaExec>("props"){
        main = "org.scalacheck.Test"
        classpath(sourceSets["main"].output + sourceSets["test"].output)
        args("--plugin", "pretty", "--glue", "", "src/test/resources")
    }
    register<JavaExec>("cuke"){
        main ="cucumber.api.cli.Main"
        classpath(configurations["cucumberRuntime"] + sourceSets["main"].output + sourceSets["test"].output)
        args("--plugin", "pretty", "--glue", "", "src/test/resources")
    }
    register<JavaExec>("scalaTest") {
        dependsOn(listOf("testClasses"))
        main = "org.scalatest.tools.Runner"
        args("-R", "build/classes/scala/test build/classes/java/test", "-o")
        classpath(sourceSets["test"].runtimeClasspath)
    }
    named<Test>("test") {
        //dependsOn("scalaTest")
        // useJUnit() // JUnit 4 runner infrastructure
        // useJUnitPlatform() // enable JUnit Platform (aka JUnit 5) support
        // useJUnitPlatform { includeEngines("junit-vintage")  } // JUnit 4 tests on JUnit Platform
        useJUnitPlatform { includeEngines("junit-vintage", "junit-jupiter", "scalatest", "junit-platform-suite")  } // JUnit 4 + JUnit 5 + ScalaTest
        testLogging.events("failed","passed","skipped")
        testLogging.showStandardStreams = true
        testLogging.displayGranularity = 0 // max granularity
        systemProperties(System.getProperties().map { Pair<String,String>(it.key.toString(), it.value.toString()) }.toMap())
    }
}


// gradle test --rerun-tasks
// gradle cuke --rerun-tasks
//   In IntelliJ, run Cucumber features via RunCucumberTests
// gradle reportScoverage
// gradle test --tests *MyClass