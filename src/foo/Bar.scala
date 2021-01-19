package foo

import scalachain._

class Bar {
  val chain = new BlockChain(1)
  val block = chain.newBlock("Message")
  chain.addBlock(block)
}
