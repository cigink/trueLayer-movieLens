package com.koshy.cigin

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.col

trait DataFilters {
  
  def budgetFilter(df:DataFrame): DataFrame = {
    df.filter(col("budget")=!=0 && col("budget") >= 10000)
  }

  def revenueFilter(df:DataFrame): DataFrame = {
    df.filter(col("revenue")=!=0 && col("revenue") >= 400)
  }

}

object DataFilters extends DataFilters
