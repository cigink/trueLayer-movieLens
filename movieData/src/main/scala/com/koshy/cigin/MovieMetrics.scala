package com.koshy.cigin

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col, expr}

trait MovieMetrics {
  
  def avgRatingMetric(df: DataFrame): DataFrame = {
    df.groupBy("movieId")
      .agg(expr("avg(rating)")
        .as("avg_rating"))
  }
  
  def ratioMetric(df:DataFrame): DataFrame = {
    df.withColumn("ratio", col("budget")/col("revenue"))
  }
}

object MovieMetrics extends MovieMetrics