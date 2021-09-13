package org.koshy.cigin

import DataFilters.{budgetFilter, extractProductionCompanies, extractYear, revenueFilter}
import MovieMetrics.{avgRatingMetric, ratioMetric}

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{col, regexp_replace}

object MovieRunner {

  def main(args: Array[String]) {
    val spark = SparkSession.builder()
      .appName("movie_lens")
      .master("local")
      .getOrCreate()

    val reader = new FileReader(spark)

    val movieMetaDataDf = reader
      .csvReader("archive/movies_metadata.csv",
        List("id", "title", "budget", "release_date", "revenue", "production_companies"),
        Schema.movieSchema)
      .transform(extractYear)
      .transform(extractProductionCompanies)
      .transform(budgetFilter)
      .transform(revenueFilter)
    
    
    val ratingsDf = reader
      .csvReader("archive/ratings.csv",
        List("movieId", "rating"),
        Schema.ratingSchema)
      .withColumnRenamed("movieId", "id")
      .transform(avgRatingMetric).cache()


    val movieMetaRatingDf = movieMetaDataDf.join(ratingsDf, Seq("id"), "left")
      .drop("movieId")
      .transform(ratioMetric)


    val wikiDf = reader.xmlReader("archive/enwiki-latest-abstract 2.xml", Schema.wikiSchema)
      .withColumn("title", regexp_replace(col("title"), "Wikipedia: ", ""))

    val movieWithWikiDf = movieMetaRatingDf
      .join(wikiDf, Seq("title"), "left")
      .select(Schema.outputSchema.map(field => col(field.name)): _*)
      .sort(col("ratio").desc)

    DbWriter.writeTodb(movieWithWikiDf.limit(1000))
  
  }
}
