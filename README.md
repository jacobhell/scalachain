# scalachain
Blockchain Implementation Written in Scala

To use:

```scala
import scalachain._

val chain = new BlockChain(1)
val block = chain.newBlock("Message")
chain.addBlock(block)
```
