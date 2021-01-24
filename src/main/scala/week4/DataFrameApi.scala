package week4

import week4.MyContext._
import org.apache.spark.sql.DataFrame
import mySpark.implicits._

object DataFrameApi extends App {

  case class Employee(id: Int, fname: String, lname: String, age: Int, city: String)
  val employee1 = Employee(1, "james", "palmer", 24, "london")
  val employee2 = Employee(1, "lebron james", "p", 36, "los angeles")
  val employeeDf: DataFrame = sc.parallelize(List(employee1, employee2)).toDF()
//  val dfWithoutSchema: DataFrame = mySpark.createDataFrame(sc.parallelize(List(employee1, employee2)))

  val londonEmployees = employeeDf.select("id", "lname")
                                  .where("city == 'london'")
                                  .orderBy("id")

  londonEmployees.show()

  /*
  +---+------+
| id| lname|
+---+------+
|  1|palmer|
+---+------+
   */
}
