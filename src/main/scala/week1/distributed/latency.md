Distribution introduces important concerns

- *Partial failure*: crash failures of a subset of the machines involved
                     in a distributed computation
  
- *Latency*: certain operations have a much higher latency than other operations due to
             operations due to network communication
  
Spark handles these two issues particularly well

[important latency numbers](https://www.freecodecamp.org/news/must-know-numbers-for-every-computer-engineer/)

Reading from memory cheaper than reading from disk

Memory generally cheaper than disk and/or network

Fault tolerance with Hadoop came at a cost: shuffled its data and wrote to disk 
but we know disk much slower than memory 

By keeping data immutable and in memory (note similarities to scala)
fault tolerance is achieved by replaying functional transformations

Goal is to minimize network traffic