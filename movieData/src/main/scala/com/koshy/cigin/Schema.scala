package com.koshy.cigin

import org.apache.spark.sql.types.{FloatType, StringType, StructField, StructType}

object Schema {
    val Ratingschema: StructType = StructType(
      List(
        StructField("userId", StringType),
        StructField("movieId", StringType),
        StructField("rating", FloatType),
        StructField("timestamp", StringType)
      ))

  val Movieschema: StructType = StructType(
    List(
      StructField("id", StringType),
      StructField("title", StringType),
      StructField("budget", FloatType),
      StructField("release_date", StringType),
        StructField("revenue", FloatType)
    ))
}
