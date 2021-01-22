package scalachain

import com.google.gson.{Gson, JsonArray, JsonElement, JsonSerializationContext, JsonSerializer}

import java.lang.reflect.Type

class BlockChainSerializer extends JsonSerializer[BlockChain] {
  override def serialize(src: BlockChain, typeOfSrc: Type, context: JsonSerializationContext): JsonElement = {
    val gson = new Gson()
    val arrayOfBlocks = new JsonArray()
    src.blocks.foreach(block => {
      val jsonObject = gson.toJsonTree(block)
      arrayOfBlocks.add(jsonObject)
    })

    arrayOfBlocks
  }
}
