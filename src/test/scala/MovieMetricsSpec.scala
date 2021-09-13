package org.koshy.cigin

import org.apache.spark.sql.SparkSession
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MovieMetricsSpec extends AnyFlatSpec with Matchers {

  val spark = SparkSession.builder
    .master("local[*]")
    .getOrCreate()
  
  val fileReader = new FileReader(spark)
  

  "A rating" should "be the average of all rating of that movie" in {
    val input = fileReader.csvReader("src/test/resources/input/ratings.csv", List("movieId", "rating"), Schema.ratingSchema)
      .withColumnRenamed("movieId", "id")
    val actualDf = MovieMetrics.avgRatingMetric(input)
    val expectedDf = fileReader.csvReader("src/test/resources/output/ratings_expected.csv", List("id", "rating"), TestSchema.ratingOutputTestSchema)
    
    actualDf.collect() should contain theSameElementsAs( expectedDf.collect())
    
  }

  "A ratio" should "be the budget divided by revenue" in {

    val input = fileReader.csvReader("src/test/resources/input/movie_meta.csv", List("id", "title", "budget", "release_date", "revenue", "production_companies"),
      Schema.movieSchema)
    val actualDf = MovieMetrics.ratioMetric(input)
    val expectedDf = fileReader.csvReader("src/test/resources/output/movie_meta_expected.csv", List("id", "title", "budget", "release_date", "revenue", "production_companies", "ratio"),
      TestSchema.movieOutputTestSchema)
    
    actualDf.collect() should contain theSameElementsAs( expectedDf.collect())

  }

}
