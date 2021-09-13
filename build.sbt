name := "true-layer-challenge"

version := "1.0"

scalaVersion := "2.11.8"

idePackagePrefix := Some("org.koshy.cigin")


  scalaVersion := "2.11.0"
  libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.4" % "provided"
  libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.4" % "provided"
  libraryDependencies += "com.databricks" %% "spark-xml" % "0.12.0"
  libraryDependencies += "org.postgresql" % "postgresql" % "42.2.23"
  libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4"




//lazy val movieDataIo = Project(id = "movie-data-pipeline-io", base = file("movieDataIo")).settings(commonSettings: _*)

//mainClass := Some("com.koshy.cigin.MovieRunner")
//
lazy val assemblySettings = Seq(
  assemblyMergeStrategy in assembly := {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case _                             => MergeStrategy.first
  }
)

