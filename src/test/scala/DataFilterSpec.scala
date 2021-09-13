package org.koshy.cigin

import org.apache.spark.sql.SparkSession
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class DataFilterSpec extends AnyFlatSpec with Matchers {

  val spark = SparkSession.builder
    .master("local[*]")
    .getOrCreate()

  "Budget" should "be greater than 10000" in {
    val input = spark.read.csv("")
    val actualDf = MovieMetrics.avgRatingMetric(input)
    val expectedDf = spark.read.csv("")
    
    assert(actualDf == expectedDf)
  }

  "Revenue" should "be greater than 400" in {
    val input = spark.read.csv("")
    val actualDf = MovieMetrics.avgRatingMetric(input)
    val expectedDf = spark.read.csv("")

    assert(actualDf == expectedDf)

  }

  "Year" should "be extracted from release_date" in {
    val input = spark.read.csv("")
    val actualDf = MovieMetrics.avgRatingMetric(input)
    val expectedDf = spark.read.csv("")

    assert(actualDf == expectedDf)

  }

  "Production company" should "be extracted the array" in {
    val input = spark.read.csv("")
    val actualDf = MovieMetrics.avgRatingMetric(input)
    val expectedDf = spark.read.csv("")

    assert(actualDf == expectedDf)

  }

}
