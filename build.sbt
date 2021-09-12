name := "true-layer-challenge"

version := "1.0"

scalaVersion := "2.11.8"

idePackagePrefix := Some("org.koshy.cigin")

val commonSettings = Seq(
  scalaVersion := "2.11.0",
  libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.4",
  libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.4",
  libraryDependencies += "com.databricks" %% "spark-csv" % "1.5.0",
  libraryDependencies += "com.databricks" %% "spark-xml" % "0.12.0"
)


lazy val movieData = Project(id = "movie-data-pipeline", base = file("movieData")).settings(commonSettings: _*)
lazy val dbUpdater = Project(id = "db-updater", base = file("dbUpdate")).settings(commonSettings: _*)
