package com.koshy.cigin

import com.databricks.spark.xml.XmlDataFrameReader
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, regexp_replace, substring}

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
        Schema.movieSchema)
      .transform(budgetFilter)
      .transform(revenueFilter)
      .transform(extractYear)

    movieMetaDataDf.show(5, false)
    
/*    val ratingsDf = reader
      .csvReader("archive/ratings.csv",
        List("movieId", "rating"),
        Schema.ratingSchema)
      .transform(avgRatingMetric)
    
    val movieRatingDf = movieMetaDataDf.join(ratingsDf,col("movieId") === col("id"), "left")
      .drop("movieId")
      .transform(ratioMetric)
      .transform(ratioMetric)
    
//    movieRatingDf.sort(col("ratio").asc)show(1000, false)
    
    val wikiDf = reader.xmlReader("archive/enwiki-latest-abstract 2.xml", Schema.wikiSchema)
      .withColumn("title", regexp_replace(col("title"), "Wikipedia: ", ""))
      .withColumnRenamed("title", "movieTitle")

    val movieWithWikiDf = movieMetaDataDf.join(wikiDf, col("title") === col("movieTitle"), "left") 
    
    movieWithWikiDf.show(10, false)*/
  }
}
