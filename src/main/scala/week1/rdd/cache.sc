import org.apache.spark.rdd.RDD

val lastYearLogs: RDD[String] = ???
val logWithErrors = lastYearLogs.filter(_.contains("ERROR")).persist()
val firstLogsWithErrors = logWithErrors.take(10)

/**
 * Here we cache logsWithErrors in memory
 *
 * After firstLogsWithErrors is computed, Spark will store the contents of logWithErrors for faster access
 * in future operations should we reuse it
 */

val numErrors = logWithErrors.count() // now faster

/**
 * Speeds up evaluation as can be reused on iterations, rather than recomputing each time
 */