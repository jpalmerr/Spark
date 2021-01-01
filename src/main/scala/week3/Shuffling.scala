package week3

import week3.MyData._

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
