import AssemblyKeys._ // put this at the top of the file

seq(assemblySettings: _*)

seq(ScctPlugin.instrumentSettings : _*)

test in assembly := {}

mainClass in assembly := Some("com.rowehl.mlia.KNN")

name := "scala_ml"

version := "1.0"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.0.M1" % "test"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.0.1" // JSON

libraryDependencies  ++= Seq(
            // other dependencies here
            // pick and choose:
            "org.scalanlp" %% "breeze-math" % "0.1-SNAPSHOT",
            "org.scalanlp" %% "breeze-learn" % "0.1-SNAPSHOT",
            "org.scalanlp" %% "breeze-process" % "0.1-SNAPSHOT"
)

resolvers ++= Seq(
            // other resolvers here
            "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
)

scalaVersion := "2.9.2"

scalacOptions += "-deprecation"
