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
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % "test"
libraryDependencies += "org.mockito" %% "mockito-scala" % "1.16.23" % "test"


lazy val assemblySettings = Seq(
  assemblyMergeStrategy in assembly := {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case _                             => MergeStrategy.first
  }
)

