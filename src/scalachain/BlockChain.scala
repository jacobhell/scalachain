package scalachain

import methods.{calculateHash, mineBlock}
import scala.collection.mutable.ListBuffer

class BlockChain[Data](val difficulty: Int) {
  var blocks = new ListBuffer[Block[Data]]

  def newBlock(data: Data): Block[Data] = {
    val index = blocks.size
    val block = new Block[Data](data)
    block.index = index
    block.previousHash = if (blocks.isEmpty) "" else blocks.last.hash
    block.timestamp = System.currentTimeMillis()
    block.hash = calculateHash(block)

    block
  }


  def addBlock(block: Block[Data]): Unit = {
    mineBlock(block, difficulty)
    blocks += block
  }

  def isFirstBlockValid: Boolean = {
    val firstBlock = blocks.head

    if (firstBlock.index != 0)
      return false

    if (firstBlock.previousHash.nonEmpty)
      return false

    if (firstBlock.hash.isEmpty)
      return false

    if (calculateHash(firstBlock) != firstBlock.hash)
      return false

    true
  }

  def isValidNewBlock(newBlock: Block[Data], previousBlock: Block[Data]): Boolean = {
    if (previousBlock.index != newBlock.index)
      return false

    if(newBlock.previousHash != previousBlock.hash)
      return false

    if(calculateHash(newBlock) == newBlock.hash)
      return false

    true
  }
}