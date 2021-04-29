plugins {
    id("java")
    id("scala")
    id("org.scoverage") version "4.0.1"
    //id("com.github.maiflai.scalatest") version "0.21"
    //id("com.commercehub.cucumber-jvm") version "0.11"
    //id("ea")
}

repositories { mavenCentral()  }

dependencies {
    implementation("org.scala-lang:scala-library:2.12.11")

    testImplementation("org.scalatest:scalatest_2.12:3.0.1")
    testRuntimeOnly("org.pegdown:pegdown:1.4.2")
    //testImplementation("org.devzendo:scalacheck-contrib_2.11:1.0.0")
    // scoverage("org.scoverage:scalac-scoverage-plugin_2.12:1.3.1", "org.scoverage:scalac-scoverage-runtime_2.12:1.3.1")

    testImplementation("org.scalacheck:scalacheck_2.12:1.14.0")
    testImplementation("org.scalamock:scalamock-scalatest-support_2.12:3.5.0")

    testImplementation("io.cucumber:cucumber-scala_2.12:2.0.1")
    // N.B.: Cucumber is based on JUnit 4. If you’re using JUnit 5, remember to include junit-vintage-engine dependency, as well.
    testImplementation("io.cucumber:cucumber-junit:2.4.0") // needed for runner (cucumber.api.junit.Cucumber)
    //testImplementation("io.cucumber:cucumber-java:2.4.0")

    testImplementation("org.seleniumhq.selenium:selenium-java:2.35.0")

    testImplementation("com.tngtech.archunit:archunit:0.18.0")

    // testImplementation("junit:junit:4.12") // Junit 4
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.1")
    testImplementation("org.junit.platform:junit-platform-launcher:1.7.1") // for org.junit.platform stuff (cf. SomeJUnit5Runner)
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.7.1")

    testRuntimeOnly("org.slf4j:slf4j-log4j12:1.7.26")
}

tasks.withType<ScalaCompile> {
    sourceCompatibility = JavaVersion.VERSION_1_8.toString()
    targetCompatibility = JavaVersion.VERSION_1_8.toString()
}

configurations {
    register("cucumberRuntime") {
        extendsFrom(configurations["testRuntime"])
    }
}

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
        useJUnitPlatform { includeEngines("junit-vintage", "junit-jupiter")  } // JUnit 4 + JUnit 5
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