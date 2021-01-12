# Unstructured vs structured data

When we have multiple options for a solution, for optimisation purposes,
choose where the join is on the smallest data set

Given a bit of extra structural information, spark can make these optimisations for you.

**Structured** - fixed schema (sql tables etc)
**Semi Structured** -- json, xml
**Unstructured** - file dumps, images

With RDDs we have been reading in data that isn't structured.

If the data is structured (like in a db) Spark can see the structure of the dataset => optimize

The same goes for computation.

SparkSQL makes this possible