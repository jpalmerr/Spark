package week1.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**

Transformations: RDD => RDD
 lazy - result not immediately computed

 Actions: RDD => some sort of value
 eager - result immediately computed

 this is how we reduce network connection

 big benefit of transformations being lazy: we can apply optimisations on them
 */

object TransformationsActions extends App {

  val spark:SparkSession = SparkSession.builder().master("local[1]")
    .appName("SparkExample")
    .getOrCreate()

  val sc = spark.sparkContext // some basic set up for example purposes

  val largeList: List[String] = List("a", "bc", "def", "hijk")
  val wordsRdd: RDD[String] = sc.parallelize(largeList)
  val lengthRdd: RDD[Int] = wordsRdd.map(_.length)
  val totalChars: Int = lengthRdd.reduce(_ + _)

  println(s"total characters: $totalChars")
}

/**
 * Transformations on two RDDS
 *
 * supports set-like operations
 *
 * union: Return an RDD containing elements from both RDDS
 * intersection: Return an RDD containing elements only found in both RDDs
 * subtract: Return an RDD with the contents of the other RDD removed
 * cartesian[U]: Cartesian Product with the other RDD - RDD[(T, U)]
 *
 *
 *
 * Actions (that don't exist in scala)
 *
 * takeSample(withRepl: Boolean, num: Int): Array[T]
 *    return array with random sample, with or without replacement
 *
 * takeOrdered(num: Int)(implicit ord: Ordering[T]): Array[T]
 *    return first n elements using order
 *
 * saveAsTestFile(path: String): Unit
 */
