# How Spark jobs are executed

Master -> Driver program

Workers -> Worker nodes

Driver program has the spark context 

They communicate via cluster managers

Master:
 - where `main()` lies
 - creates context, reates RDDs, ends off transformations/actions

Executors:
 - run the tasks 
 - return computed results to driver
 - provide in memory storage for cached RDDs

SparkContext is sending tasks for executors to run

