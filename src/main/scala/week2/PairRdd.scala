package week2

import org.apache.spark.rdd.RDD
import MyContext.sc

object PairRdd extends App {

  case class Event(organizer: String, name: String, budget: Int)

  val e1 = Event("abc", "123", 1000)
  val e2 = Event("def", "456", 2000)
  val e3 = Event("ghi", "789", 3000)
  val e4 = Event("abc", "101112", 4000)
  val e5 = Event("def", "131415", 5000)

  val events: Seq[Event] = List(e1, e2, e3, e4, e5)

  val eventsRdd: RDD[(String, Int)] = sc.parallelize(events).map { event =>
    (event.organizer, event.budget)
  }

  val groupedRdd = eventsRdd.groupByKey()

  groupedRdd.collect().foreach(println)

  val budgetsRdd = eventsRdd.reduceByKey(_ + _)
  budgetsRdd.collect().foreach(println)

  val intermediate: RDD[(String, (Int, Int))] =
    eventsRdd.mapValues(b => (b, 1)).reduceByKey((v1, v2) => (v1._1 + v2._1, v1._2 + v2._2))

  val avgBudget= intermediate.mapValues {
    case (budget, numberOfEvents) => budget / numberOfEvents
  }

  avgBudget.collect().foreach(println) // organiser, average budget
}


object TransformKeys extends App {

  case class Visitor(ip: String, timestamp: String, duration: String)

  val v1 = Visitor("ipA", "timestamp1", "duration1")
  val v2 = Visitor("ipB", "timestamp2", "duration2")
  val v3 = Visitor("ipC", "timestamp3", "duration3")
  val v4 = Visitor("ipA", "timestamp4", "duration4")
  val v5 = Visitor("ipA", "timestamp5", "duration5")

  val visitors = List(v1, v2, v3, v4, v5)

  val visits: RDD[Visitor] = sc.parallelize(visitors)

  val visitorMap: RDD[(String, String)] = visits.map(v => (v.ip, v.duration))

  val numOfUniqueVisits = visitorMap.keys.distinct().count()

  println(numOfUniqueVisits)

}