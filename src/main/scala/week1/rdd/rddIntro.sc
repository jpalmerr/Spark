import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/*
val encyclopedia: RDD[String] = ???
val result = encyclopedia.filter(page => page.contains("EPFL")).count()
 */

val list = List("abc", "def", "ghi", "jkl")

val spark:SparkSession = SparkSession.builder().master("local[1]")
  .appName("SparkExample")
  .getOrCreate()

val sc = spark.sparkContext

val myRdd: RDD[String] = sc.parallelize(list)

val count: RDD[(String, Int)] = myRdd.flatMap { line =>
  line.split(" ")
    .map { word =>
      (word, 1)
    }
}.reduceByKey(_+ _)
