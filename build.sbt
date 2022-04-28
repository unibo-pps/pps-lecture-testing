ThisBuild / resolvers += Resolver.jcenterRepo

val junitJupiterVersion = "5.7.1"
val junitPlatformVersion = "1.8.2"

lazy val root = (project in file("."))
  .settings(
    scalaVersion := "3.1.2",
    name := "pps-lecture-testing",
    // javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),
    libraryDependencies ++= Seq(
      // "junit" % "junit" % "4.12" % Test, // Junit 4
      "com.novocode" % "junit-interface" % "0.11" % Test, // sbt's test interface for JUnit 4
      "org.junit.jupiter" % "junit-jupiter-api" % junitJupiterVersion % Test, // aggregator of junit-jupiter-api and junit-jupiter-engine (runtime)
      "org.junit.jupiter" % "junit-jupiter-engine" % junitJupiterVersion % Test, // for org.junit.platform
      "org.junit.vintage" % "junit-vintage-engine" % junitJupiterVersion % Test,
      "org.junit.platform" % "junit-platform-launcher" % junitPlatformVersion % Test,
      "org.junit.platform" % "junit-platform-commons" % junitPlatformVersion % Test,
      "org.junit.platform" % "junit-platform-engine" % junitPlatformVersion % Test,
      "org.junit.platform" % "junit-platform-suite-api" % junitPlatformVersion % Test,
      "net.aichler" % "jupiter-interface" % "0.8.4" % Test,
      "org.scalatest" %% "scalatest" % "3.2.11" % Test,
      //"org.seleniumhq.selenium" % "selenium-java" % "2.35.0" % Test,
      "org.scalatestplus" %% "selenium-4-1" % "3.2.11.0" % Test,
      "io.cucumber" %% "cucumber-scala" % "8.2.6" % Test,
      "io.cucumber" % "cucumber-junit-platform-engine" % "7.3.2" % Test,
      //"org.scalacheck" %% "scalacheck" % "1.16.0" % Test,
      "org.scalatestplus" %% "scalacheck-1-15" % "3.2.10.0" % Test,
      // "org.scalamock" %% "scalamock" % "5.1.0" % Test, // not support for Scala 3
      "org.scalatestplus" %% "mockito-3-12" % "3.2.10.0" % Test,
      "com.tngtech.archunit" % "archunit" % "0.18.0" % Test,
      "org.slf4j" % "slf4j-log4j12" % "1.7.26" % Test
),
    crossPaths := false, // https://github.com/sbt/junit-interface/issues/35
    Test / parallelExecution := false
  )

// Cucumber configuration
// Run by:  sbt> cucumber
enablePlugins(CucumberPlugin)
CucumberPlugin.glues := List("testLecture/code/e4bdd/steps")
