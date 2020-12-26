# Data Parallel

We'd like to exploit the ability of the computers multiple processors to try
and compute this data more quickly by doing it in parallel,

- by breaking up the work and trying to do many things at once

Consider a shared collection looking like a map each element function

### Shared memory data parallelism :

- split the data
- threads independently operate on the data shards in parallel
- combine when done (if necessary)

**Scala's Paraellel Collection** is a collection abstraction over shared memory data-parallel execution

### Distributed data parallelism :

- split the data **over several nodes**
- **nodes** independently operate on the data shards in parallel
- combine when done (if necessary)

New concern - now we have to worry about **network latency** between workers.

----------

Spark implements a **distributed data parallel model** called
**Resilient Distributed Datasets (RDDs)**

