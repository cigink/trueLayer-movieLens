package com.koshy.cigin

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col, to_timestamp, year}

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
      .withColumnRenamed("year","release_date")
  }
  
  def extractProductionCompanies(df:DataFrame):DataFrame = {
    ???
  }
}

object DataFilters extends DataFilters
