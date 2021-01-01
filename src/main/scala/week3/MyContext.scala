package week3

import org.apache.spark.sql.SparkSession

object MyContext {

  val spark: SparkSession = SparkSession.builder().master("local[1]")
    .appName("Week3")
    .getOrCreate()

  val sc = spark.sparkContext

}
