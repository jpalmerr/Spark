import org.apache.spark.rdd.RDD

case class WikiPage(title: String, text: String)

val rdd: RDD[WikiPage] = ???

val pairRdd: RDD[(String, String)] = rdd.map(page => (page.title, page.text))
