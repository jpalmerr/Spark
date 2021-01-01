package week3

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object Shuffling extends App {

  import MyContext._


  val pairs = sc.parallelize(List((1, "one"), (2, "two"), (3, "three")))
  pairs.groupByKey() // ShuffledRDD[1]

 /*
 * We typically have to move data from one node to another to be
 * "grouped with" its key
 * Doing this is called "shuffling"
 *
 * Shuffles can be an enormous hit to efficiency
 */

  case class CFFPurchase(customerId: Int, destination: String, price: Double)

  val CFFList: List[CFFPurchase] = List(
    CFFPurchase(100, "Geneva", 22.25), CFFPurchase(300, "Zurich", 42.10), CFFPurchase(100, "Fribourg", 12.40),
    CFFPurchase(200, "St Gallen", 8.20), CFFPurchase(100, "Lucerne", 31.60), CFFPurchase(300, "Basel", 16.20)
  )

  val purchasesRdd: RDD[CFFPurchase] = sc.parallelize(CFFList)

  // how many trips, how much money spent by each individual customer

  val purchasesPerMonth =
    purchasesRdd.map(p => (p.customerId, p.price)) // PairRDD
      .groupByKey() // RDD[(K, Iterable[V])]
      .mapValues(p => (p.size, p.sum))
      .collect()

  purchasesPerMonth.foreach(println)

  val optimizedPurchasesPerMonth =
    purchasesRdd.map(p => (p.customerId, (1, p.price))) // (customerId, (numTrips, totalSpent))
      .reduceByKey((v1, v2) => (v1._1+ v2._1, v1._2 + v1._2)) // (num + num, spent + spent)
      .collect()

  optimizedPurchasesPerMonth.foreach(println)
}
