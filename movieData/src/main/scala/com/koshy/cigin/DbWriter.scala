package com.koshy.cigin

import org.apache.spark.sql.DataFrame

object DbWriter {
  
  def writeTodb(df:DataFrame) = {

    df.write
      .format("jdbc")
      .option("url", "jdbc:postgresql://localhost:5432/postgres")
      .option("dbtable", "public.moviewiki")
      .option("user", "postgres")
      .option("password", "postgres")
      .option("driver", "org.postgresql.Driver")
      .mode("overwrite")
      .save
  }

}
