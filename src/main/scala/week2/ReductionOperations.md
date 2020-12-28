Reduction Operations walk through a collection and combine neighbouring 
elements of the collection together to produce a single combined result

`foldLeft` is NOT parallelizable
`fold` is as forces us to return same type

`aggregate` is the most commonly used reduction method as you will find in SPark use cases we are often projecting down
from more complex data types