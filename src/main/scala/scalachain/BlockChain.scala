package scalachain

import methods.{calculateHash, mineBlock}
import scala.collection.mutable.ListBuffer

class BlockChain (val difficulty: Int) {
  var blocks = new ListBuffer[Block]

  def newBlock(string: String): Block = {
    val index = blocks.size
    val block = new Block(string)
    block.index = index
    block.previousHash = if (blocks.isEmpty) "" else blocks.last.hash
    block.timestamp = System.currentTimeMillis()
    block.hash = calculateHash(block)

    block
  }


  def addBlock(block: Block): Unit = {
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

  def isValidNewBlock(newBlock: Block, previousBlock: Block): Boolean = {
    if (previousBlock.index != newBlock.index)
      return false

    if(newBlock.previousHash != previousBlock.hash)
      return false

    if(calculateHash(newBlock) == newBlock.hash)
      return false

    true
  }
}