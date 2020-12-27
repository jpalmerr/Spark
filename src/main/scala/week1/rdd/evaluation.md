### Caching

By default, RDDs are recomputed each time you run an action on them. 
This can be expensive.

    Spark allows you to control what is cached in memory

To cache RDD in memory simply call `cache()` or `persit()`

 - **cache**: commit to memory
- **persist**: commit to disk

What happens is Spark only holds as much as it can in memory and anything else that it can't hold in memory,
it throws it out.

If ever those pieces of data need to be reused, then it will re-evaluate them.
It will go through all those many transformations that are required
to reevaluate the missing pieces of data it doesn't have.