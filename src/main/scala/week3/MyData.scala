package week3

import org.apache.spark.rdd.RDD
import week3.MyContext.sc

object MyData {

  case class CFFPurchase(customerId: Int, destination: String, price: Double)

  val CFFList: List[CFFPurchase] = List(
    CFFPurchase(100, "Geneva", 22.25), CFFPurchase(300, "Zurich", 42.10), CFFPurchase(100, "Fribourg", 12.40),
    CFFPurchase(200, "St Gallen", 8.20), CFFPurchase(100, "Lucerne", 31.60), CFFPurchase(300, "Basel", 16.20)
  )

  val purchasesRdd: RDD[CFFPurchase] = sc.parallelize(CFFList)

}
