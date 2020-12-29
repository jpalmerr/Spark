package week2

import org.apache.spark.rdd.RDD
import week2.MyContext.sc

/**
 * Join - take pair rdds return 1
 *
 * Inner join (join)
 * Outer joins (leftOuterJoin/rightOuterJoin)
 *
 * When we try to join two RDDs containing different customer ids:
 *  inner join - combined pairs whose keys are present in both input RDDs
 *  outer join - combined pairs whose keys don't need to be present in each
 *      - right and left allow us to prioritize keys
 *        - leftOuterJoin[W](other: RDD[(K, W)]: RDD[(K, (V, Option[W]))]
 *        - rightOuterJoin[W](other: RDD[(K, W)]: RDD[(K, (Option[V], W))]
 */

object Joins extends App {

  import Models._

  val as = List((101, ("Reutli", AG)), (102, ("Brelaz", DemiTarif)), (103, ("Gress", DemiTarifVisa)), (104, ("Schatten", DemiTarif)))

  val subscriptions: RDD[(Int, (String, Discount))] = sc.parallelize(as)

  val ls = List((101, Bern), (101, Thun), (102, Lusanne), (102, Geneve), (102, Zurich), (103, StGallen), (103, Chur))

  val locations: RDD[(Int, City)] = sc.parallelize(ls)

  // its possible for a customer to be in both datasets or only in one

  // how do we combine only customers that have a subscription and where there is local info?
  val trackedCustomers: RDD[(Int, ((String, Discount), City))] = subscriptions.join(locations)

  trackedCustomers.collect().foreach(println)


  /* CFF wants to know for which subscribers the CFF app has managed to collect location information
   * its possible someone has a demi-tarif, but doesn't use CFF app
   */

  val subsWithOptionalLocations = subscriptions.leftOuterJoin(locations)

  subsWithOptionalLocations.collect().foreach(println)

}

object Models {
  trait Discount
  case object AG extends Discount
  case object DemiTarif extends Discount
  case object DemiTarifVisa extends Discount

  trait City
  case object Bern extends City
  case object Thun extends City
  case object Lusanne extends City
  case object Geneve extends City
  case object Zurich extends City
  case object StGallen extends City
  case object Chur extends City

}