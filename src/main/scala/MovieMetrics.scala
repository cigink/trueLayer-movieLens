package org.koshy.cigin

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col, expr}

trait MovieMetrics {
  
  def avgRatingMetric(df: DataFrame): DataFrame = {
    df.groupBy("id")
      .agg(expr("avg(rating)")
        .as("avg_rating"))
      .drop("rating")
      .withColumnRenamed("avg_rating", "rating")
  }
  
  def ratioMetric(df:DataFrame): DataFrame = {
    df.withColumn("ratio", col("budget")/col("revenue"))
  }
}

object MovieMetrics extends MovieMetrics
