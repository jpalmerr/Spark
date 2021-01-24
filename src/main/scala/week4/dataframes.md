# Data Frames

- a relational api built over Spark `RDD`s
- able to be automatically aggressively optimized
- untyped

To enable optimization, Spark's SQL dataframes operate on a restricted set of data types.
We can do Arrays, Maps, or Structs (case classes) though!

```scala
import org.apache.spark.sql.types._

case class Person(name: String, age: Int)

StructType(List(StructField("name", StringType, true),
          StructField("age", StringType, true)))   
```

We can get pretty complex with these nested types.

```scala
case class Employee(id: Int, name: String)
val employeeDF = sc.parallelize().toDF
employeeDF.show() // pretty prints first 20 elements
employeeDF.printSchema() // prints schema in tree format
```

Transformations on DataFrames are 
    - operations which return a DataFrame as a resuly
    - evaluated lazily

You can work with columns in 3 ways

```scala
df.filter(${"age"} > 18) // import spark.implicits._
df.filter(df("age" > 18))
df.filter("age > 18")
```

