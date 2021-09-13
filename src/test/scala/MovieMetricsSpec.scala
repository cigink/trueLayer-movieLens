package org.koshy.cigin

import org.apache.spark.sql.SparkSession
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class MovieMetricsSpec extends AnyFlatSpec with Matchers {

  val spark = SparkSession.builder
    .master("local[*]")
    .getOrCreate()
  

  "A rating" should "be the average of all rating of that movie" in {
    val input = spark.read.csv("")
    val actualDf = MovieMetrics.avgRatingMetric(input)
    val expectedDf = spark.read.csv("")
    
    assert(actualDf == expectedDf)
  }

  "A ratio" should "be the budget divided by revenue" in {

    val input = spark.read.csv("")
    val actualDf = MovieMetrics.ratioMetric(input)
    val expectedDf = spark.read.csv("")

    assert(actualDf == expectedDf)

  }

}
