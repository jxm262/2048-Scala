name := "2048-Scala"

version := "1.0"

scalaVersion := "2.10.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.2",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.2",
  "org.apache.logging.log4j" % "log4j-api" % "2.0-rc1",
  "org.apache.logging.log4j" % "log4j-core" % "2.0-rc1",
  "org.scalatest" %% "scalatest" % "2.1.4" % "test",
  "junit" % "junit" % "4.11" % "test",
  "com.novocode" % "junit-interface" % "0.10" % "test"
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")
