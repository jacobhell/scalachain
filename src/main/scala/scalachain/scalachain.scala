package scalachain

import com.google.gson.{Gson, GsonBuilder, JsonSerializer}

import java.io.{BufferedReader, FileReader}
import java.nio.file.{Files, Paths}

object scalachain {
  def writeBlockChain(chain: BlockChain): Unit = {
    val gson = new GsonBuilder()
      .registerTypeAdapter(classOf[BlockChain], new BlockChainSerializer)
      .setPrettyPrinting()
      .create()

    val json = gson.toJson(chain)
    Files.write(Paths get "blockchain.json", json getBytes)
  }

  def getBlockChain(): BlockChain = {
    val gson = new GsonBuilder()
      .registerTypeAdapter(classOf[BlockChain], new BlockChainDeserializer())
      .create()

    if (!Files.exists(Paths.get("blockchain.json"))) {
      return new BlockChain(1)
    }

    val fileReader = new BufferedReader(new FileReader("blockchain.json"))

    gson.fromJson(fileReader, classOf[BlockChain])
  }
}