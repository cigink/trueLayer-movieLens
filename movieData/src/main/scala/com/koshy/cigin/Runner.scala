package com.koshy.cigin

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col

object Runner extends DataFilters with MovieMetrics {

  def main(args: Array[String]) {
    val spark = SparkSession.builder()
      .appName("movie_lens")
      .master("local")
      .getOrCreate()
    
    val reader = new FileReader(spark)
    
    val movieMetaDataDf = reader
      .csvReader("archive/movies_metadata.csv",
        List("id","title", "budget", "release_date", "revenue"),
        Schema.movieschema)
      .transform(budgetFilter)
      .transform(revenueFilter)

    movieMetaDataDf.show(10, false)

    val ratingsDf = reader
      .csvReader("archive/ratings.csv",
        List("movieId", "rating"),
        Schema.ratingschema)
      .transform(avgRatingMetric)
    
    spark.sparkContext.broadcast(ratingsDf)

    val movieRatingDf = movieMetaDataDf.join(ratingsDf,col("movieId") === col("id"), "left")
      .drop("movieId")
      .transform(ratioMetric)
      .transform(ratioMetric)
    
    movieRatingDf.sort(col("ratio").asc)show(1000, false)
 
  }
}
