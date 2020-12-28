package week2

import org.apache.spark.sql.SparkSession

object MyContext {

  val spark: SparkSession = SparkSession.builder().master("local[1]")
    .appName("SparkExample")
    .getOrCreate()

  val sc = spark.sparkContext

}
