package org.koshy.cigin

import org.apache.spark.sql.types.{DoubleType, FloatType, IntegerType, StringType, StructField, StructType}

object TestSchema {

  val ratingOutputTestSchema: StructType = StructType(
    List(
      StructField("id", StringType),
      StructField("rating", DoubleType)
    ))

  val movieOutputTestSchema: StructType = StructType(
    List(
      StructField("id", StringType),
      StructField("title", StringType),
      StructField("budget", IntegerType),
      StructField("release_date", StringType),
      StructField("revenue", IntegerType),
      StructField("production_companies", StringType),
      StructField("ratio", DoubleType)
    ))

}
