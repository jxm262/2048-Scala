name := "2048-Scala"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "org.apache.logging.log4j" % "log4j-api" % "2.0-rc1",
  "org.apache.logging.log4j" % "log4j-core" % "2.0-rc1",
  "org.scalatest" %% "scalatest" % "2.1.4" % "test",
  "junit" % "junit" % "4.11" % "test",
  "com.novocode" % "junit-interface" % "0.10" % "test",
  jdbc,
  anorm,
  cache
)     

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")

play.Project.playScalaSettings
