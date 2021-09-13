package org.koshy.cigin

import com.typesafe.scalalogging.Logger
import org.apache.spark.sql.DataFrame

import java.sql.{Connection, DriverManager}

object DbWriter {

  val logger = Logger("DbWriter")

  val driver = "org.postgresql.Driver"
  val url = "jdbc:postgresql://host.docker.internal:5432/postgres"
  val username = "postgres"
  val password = "postgres"
  
  def writeTodb(df:DataFrame) = {
    createTable()
    df.write
      .format("jdbc")
      .option("url", url)
      .option("dbtable", "public.moviewiki")
      .option("user", username)
      .option("password", password)
      .option("driver", driver)
      .mode("overwrite")
      .save
  }
  
  def createTable() = {

    var connection:Connection = null

    try {
      logger.info("Creating table in db")
      // make the connection
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)

      // create the statement, and run the select query
      val statement = connection.createStatement()
      statement.executeUpdate(
        "CREATE table if not exists public.moviewiki" +
        "(" +
        "id int PRIMARY KEY," +
        "title varchar(255)," +
        "budget varchar(255)," +
        "year varchar(255)," +
        "revenue varchar(255)," +
        "rating varchar(255)," +
        "ratio varchar(255)," +
        "production_companies varchar(1024)," +
        "url varchar(255)," +
        "abstract varchar(255)" +
        ")"
      )
    } catch {
      case e => e.printStackTrace
    }
    connection.close()
  }
    
  }

