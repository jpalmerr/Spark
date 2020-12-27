package week1.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object Topology extends App {

  val spark:SparkSession = SparkSession.builder().master("local[1]")
    .appName("SparkExample")
    .getOrCreate()

  val sc = spark.sparkContext // some basic set up for example purposes

  case class Person(name: String, age: Int)
  val peopleList = List(Person("James", 24), Person("A", 30), Person("B", 40), Person("C", 55))


  val people: RDD[Person] = sc.parallelize(peopleList)

  val firstTwo: Array[Person] = people.take(2)
  // ends up in driver - running an action sends to nodes and then is returned

  firstTwo.foreach(person => println(person.name))
  // nothing happens on driver as foreach is an action => happening on worker nodes


}
