package main

import org.apache.logging.log4j.LogManager
import scalachain.scalachain.{getBlockChain, writeBlockChain}

object Main extends App {
  val log = LogManager.getLogger(Main.getClass())

  log.info("starting scalamessage")
  val factory = new ArgumentCommandFactory()
  val command = factory.create(args)
  command.run(args)
}

class ArgumentCommandFactory {
  def create(args: Array[String]): ArgumentCommand = {
    if (!args.isEmpty)
    {
      if (args(0) == "write")
        return new Write()
      else if (args(0) == "list")
        return new ListMessages()
    }

    new Invalid()
  }
}

class Write extends ArgumentCommand
{
  val log = LogManager.getLogger(classOf[Write])
  override def run(args: Array[String]): Unit = {
    if (args.length != 2)
    {
      log.warn("Incorrect number of arguments")
      log.info("write <message>: writes a message to the blockchain")
    }
    else
    {
      log.info("Writing Message")
      val blockChain = getBlockChain()
      val block = blockChain.newBlock(args(1))
      blockChain.addBlock(block)
      writeBlockChain(blockChain)
    }
  }
}

class ListMessages extends ArgumentCommand
{
  val log = LogManager.getLogger(classOf[ListMessages])

  override def run(args: Array[String]): Unit = {
    log.warn("listing messages")
    val blockChain = getBlockChain()
    blockChain.blocks.foreach(block => {
      println("hash: " + block.hash)
      println("nonce: " + block.nonce)
      println("index: " + block.index)
      println("timestamp: " + block.timestamp)
      println("data: " + block.data)
    })
  }
}

class Invalid extends ArgumentCommand
{
  val log = LogManager.getLogger(classOf[Invalid])
  override def run(args: Array[String]): Unit = {
    log.warn("Invalid command")
  }
}

trait ArgumentCommand
{
  def run(args: Array[String])
}