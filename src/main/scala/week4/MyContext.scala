package week4

import org.apache.spark.sql.SparkSession

object MyContext {

  val mySpark: SparkSession = SparkSession.builder().master("local[1]")
    .appName("Week4")
    .getOrCreate()

  val sc = mySpark.sparkContext

}
