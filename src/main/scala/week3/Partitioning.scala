package week3

import MyData._
import org.apache.spark.RangePartitioner
import org.apache.spark.rdd.RDD

// How does Spark know which key to put on which machine?

/**
 * The data within an RDD is split into several partitions
 *
 * Properties:
 *  - Partitions never span multiple machines
 *      i.e tuples in same partition guaranteed to be on same machine
 *  - Each machine in the cluster contains one or more partitions
 *  - no. is configurable. Default == the total number of cores on all executor nodes
 *
 * Two types:
 *  - Hash partitioning
 *  - Range partitioning
 *
 *  Customizing a partition only possible on pair RDDs
 */

object Partitioning extends App {

  // Hash partitioning attempts to spread data evenly across partitions based on the key

  val purchasesPerCustomer = purchasesRdd.map(p => (p.customerId, p.price)).groupByKey()

  /* groupByKey first computes per tuple its partition p:
  * p = k.hashCode() % numPartitions
  * Then all tuples in the same partition p are sent to the machine hosting p
  */

  // using range partitioning the distribution can be improved significantly

  // how do we set a partitioning for our data?

  val tunedPartioner = new RangePartitioner(8, purchasesPerCustomer)
  val partitioned: RDD[(Int, Iterable[Double])] = purchasesPerCustomer.partitionBy(tunedPartioner).persist()
  // why persist? would be shuffled again again again

  // partition transformations (except map/flatmap as they can change the keys)

  /* Optimizing
   * To optimize, partition the data initially => will carry it over
   */

  val optPurchasesPerCustomer = purchasesRdd.map(p => (p.customerId, p.price)).groupByKey()

  /**
   * A shuffle can occur when the resulting RDD depends on other elements from the same RDD or another RDD
   *
   * .toDebugString can help you see this
   */

}
