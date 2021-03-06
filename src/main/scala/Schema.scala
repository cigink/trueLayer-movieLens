package org.koshy.cigin

import org.apache.spark.sql.types.{FloatType, IntegerType, StringType, StructField, StructType}

object Schema {
    val ratingSchema: StructType = StructType(
      List(
        StructField("userId", StringType),
        StructField("movieId", StringType),
        StructField("rating", FloatType),
        StructField("timestamp", StringType)
      ))

  val wikiSchema: StructType = StructType(
    List(
      StructField("title", StringType),
      StructField("url", StringType),
        StructField("abstract", StringType)
    ))

  val movieSchema: StructType = StructType(
    List(
      StructField("adult", StringType),
      StructField("belongs_to_collection", StringType),
      StructField("budget", IntegerType),
      StructField("genres", StringType),
      StructField("homepage", StringType),
      StructField("id", StringType),
      StructField("imdb_id", StringType),
      StructField("original_language", StringType),
      StructField("original_title", StringType),
      StructField("overview", StringType),
      StructField("popularity", StringType),
      StructField("poster_path", StringType),
      StructField("production_companies", StringType),
      StructField("production_countries", StringType),
      StructField("release_date", StringType),
      StructField("revenue", IntegerType),
      StructField("runtime", StringType),
      StructField("spoken_languages", StringType),
      StructField("status", StringType),
      StructField("tagline", StringType),
      StructField("title", StringType),
      StructField("video", StringType),
      StructField("vote_average", StringType),
      StructField("vote_count", StringType)
    ))

  val outputSchema: StructType = StructType(
    List(
      StructField("id", StringType),
      StructField("title", StringType),
      StructField("budget", IntegerType),
      StructField("year", StringType),
      StructField("revenue", StringType),
      StructField("rating", StringType),
      StructField("ratio", StringType),
      StructField("production_companies", StringType),
      StructField("url", StringType),
      StructField("abstract", StringType)
    ))
}
