name := "akka-persistence-eventstore"

organization := "com.geteventstore"

scalaVersion := crossScalaVersions.value.last

crossScalaVersions := Seq("2.12.12", "2.13.3")

releaseCrossBuild := true

licenses := Seq("BSD 3-Clause" -> url("http://raw.github.com/EventStore/EventStore.Akka.Persistence/master/LICENSE"))

homepage := Some(new URL("http://github.com/EventStore/EventStore.Akka.Persistence"))

organizationHomepage := Some(new URL("http://geteventstore.com"))

description := "Event Store Plugin for Akka Persistence"

startYear := Some(2013)

scalacOptions ++= Seq("-target:jvm-1.8")
javacOptions  ++= Seq("-target", "8", "-source", "8")
scalacOptions --= Seq("-Ywarn-unused:params", "-Wunused:params", "-Wunused:explicits")
Compile / doc / scalacOptions ++= Seq("-groups", "-implicits", "-no-link-warnings")

///

val AkkaVersion = "2.6.8"

lazy val IntegrationTest = config("it") extend Test

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-persistence" % AkkaVersion,
  "com.typesafe.akka" %% "akka-persistence-tck" % AkkaVersion % Test,
  "com.typesafe.akka" %% "akka-persistence-query" % AkkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % AkkaVersion % Test,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream-testkit" % AkkaVersion % Test,
  "com.geteventstore" %% "eventstore-client" % "7.3.0",
  "org.specs2" %% "specs2-core" % "4.10.0" % Test,
  "org.json4s" %% "json4s-native" % "3.6.9" % Test,
  "io.spray" %% "spray-json" % "1.3.5"
)

lazy val root = (project in file("."))
  .configs(IntegrationTest)
  .settings(Defaults.itSettings: _*)
  .settings(parallelExecution in IntegrationTest := false)

pomExtra in Global := {
  <scm>
    <url>git@github.com:EventStore/EventStore.Akka.Persistence.git</url>
    <connection>scm:git:git@github.com:EventStore/EventStore.Akka.Persistence.git</connection>
    <developerConnection>scm:git:git@github.com:EventStore/EventStore.Akka.Persistence.git</developerConnection>
  </scm>
    <developers>
      <developer>
        <id>t3hnar</id>
        <name>Yaroslav Klymko</name>
        <email>t3hnar@gmail.com</email>
      </developer>
    </developers>
}

releasePublishArtifactsAction := PgpKeys.publishSigned.value

publishTo := sonatypePublishTo.value
