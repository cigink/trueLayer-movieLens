package org.koshy.cigin

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{ArrayType, StringType, StructField, StructType}

trait DataFilters {
  
  def budgetFilter(df:DataFrame): DataFrame = {
    df.filter(col("budget")=!=0 && col("budget") >= 10000)
  }

  def revenueFilter(df:DataFrame): DataFrame = {
    df.filter(col("revenue")=!=0 && col("revenue") >= 400)
  }
  
  def extractYear(df: DataFrame): DataFrame = {
    df.withColumn("year", year(to_timestamp(col("release_date"),"yyyy-mm-dd")))
      .drop("release_date")
  }
  
  def extractProductionCompanies(df:DataFrame):DataFrame = {
    df.withColumn("jsonData",
      from_json(col("production_companies"),
        ArrayType(StructType(Array(StructField("name", StringType)))))
        .getField("name"))
      .drop("production_companies")
      .withColumn("production_companies", concat_ws(", ", col("jsonData")))
      .drop("jsonData")
  }
}

object DataFilters extends DataFilters
