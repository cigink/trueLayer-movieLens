package com.koshy.cigin

import org.apache.spark.sql.functions.col
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{DataFrame, SparkSession}

class FileReader(spark: SparkSession) {
  
  def csvReader(filePath: String, fields: Seq[String], schema:StructType): DataFrame = {

    spark.read
      .option("header", "true") // Use first line of all files as header
      .option("quote", "\"")
      .option("escape", "\"")
      .schema(schema)
      .csv(filePath)
      .select(fields.map(col(_)):_*)
  }

}
