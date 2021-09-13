package org.koshy.cigin

import DataFilters.{budgetFilter, extractProductionCompanies, extractYear, revenueFilter}
import MovieMetrics.{avgRatingMetric, ratioMetric}

import com.typesafe.scalalogging.{LazyLogging, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, regexp_replace}

object MovieRunner extends LazyLogging {
  
  logger.info("App is starting")
  
  def main(args: Array[String]) {
    val spark = SparkSession.builder()
      .appName("movie_lens")
      .master("local")
      .getOrCreate()

    val reader = new FileReader(spark)

    logger.info("Reading movieMetaData")
    val movieMetaDataDf = reader
      .csvReader("archive/movies_metadata.csv",
        List("id", "title", "budget", "release_date", "revenue", "production_companies"),
        Schema.movieSchema)
      .transform(extractYear)
      .transform(extractProductionCompanies)
      .transform(budgetFilter)
      .transform(revenueFilter)

    logger.info("Reading Ratings")
    val ratingsDf = reader
      .csvReader("archive/ratings.csv",
        List("movieId", "rating"),
        Schema.ratingSchema)
      .withColumnRenamed("movieId", "id")
      .transform(avgRatingMetric).cache()

    logger.info("Joining movieMetaData with Ratings")
    val movieMetaRatingDf = movieMetaDataDf.join(ratingsDf, Seq("id"), "left")
      .drop("movieId")
      .transform(ratioMetric)

    logger.info("Reading wiki data")
    val wikiDf = reader.xmlReader("archive/enwiki-latest-abstract.xml", Schema.wikiSchema)
      .withColumn("title", regexp_replace(col("title"), "Wikipedia: ", ""))
    
    logger.info("Joining moviewithRatingData to Wiki data")
    val movieWithWikiDf = movieMetaRatingDf
      .join(wikiDf, Seq("title"), "left")
      .select(Schema.outputSchema.map(field => col(field.name)): _*)
      .sort(col("ratio").desc)

    logger.info("Writing to 1K to DB")
    DbWriter.writeTodb(movieWithWikiDf.limit(1000))
  
  }
}
