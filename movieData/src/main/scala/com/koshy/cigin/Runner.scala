package com.koshy.cigin

import org.apache.spark.sql.SparkSession

object Runner {

  def main(args: Array[String]) {
    val spark = SparkSession.builder()
      .appName("movie_lens")
      .master("local")
      .getOrCreate()
    
    val reader = new FileReader(spark)
    
    val movieMetaDataDf = reader.csvReader("archive/movies_metadata.csv", List("id","title", "budget", "release_date", "revenue"))
    
    val ratingsDf = reader.csvReader("archive/ratings.csv", List("movieId", "rating"))
    
    
  }
}
