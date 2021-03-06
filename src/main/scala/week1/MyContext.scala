package week1

import org.apache.spark.sql.SparkSession

  object MyContext {

    val spark: SparkSession = SparkSession.builder().master("local[1]")
      .appName("Week1")
      .getOrCreate()

    val sc = spark.sparkContext

  }
