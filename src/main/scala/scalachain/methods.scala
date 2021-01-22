package scalachain

import java.security.MessageDigest

object methods {
  def calculateHash(block: Block): String = {
    val index = block.index
    val timestamp = block.timestamp
    val previousHash = block.previousHash
    val data = block.data.toString
    val nonce = block.nonce
    val str = s"$index$timestamp$previousHash$data$nonce"

    stringToSHA256(str)
  }

  def stringToSHA256(string: String): String = {
    val digest = MessageDigest.getInstance("SHA-256")
    val bytes = digest.digest(string.getBytes())
    val stringBuilder = new StringBuilder()
    bytes.foreach(byte => stringBuilder.append(String.format("%02x", byte)))

    stringBuilder.toString()
  }

  def mineBlock(block: Block, difficulty: Int): Unit = {
    mineBlock(block, difficulty, 0)
  }

  def mineBlock(block: Block, difficulty: Int, nonce: Int): Unit = {
    val subString = block.hash.substring(0, difficulty)
    val zeros = createZeros(difficulty)

    block.nonce = nonce

    if (subString != zeros) {
      block.hash = calculateHash(block)
      mineBlock(block, difficulty, nonce + 1)
    }
  }

  def createZeros(numberOfZeros: Int): String = {
    if (numberOfZeros < 1)
      return ""

    var zeros = ""
    for (_ <- 1 to numberOfZeros) {
      zeros += "0"
    }

    zeros
  }
}
