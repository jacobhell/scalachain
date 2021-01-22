package scalachain

import com.google.gson.{Gson, JsonArray, JsonDeserializationContext, JsonDeserializer, JsonElement, JsonObject}

import java.lang.reflect.Type

class BlockChainDeserializer extends JsonDeserializer[BlockChain] {
  override def deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): BlockChain = {
    val arrayOfBlocks = json.asInstanceOf[JsonArray]

    val blockChain = new BlockChain(1)
    val gson = new Gson()
    arrayOfBlocks.forEach(blockJson => {
      val block = gson.fromJson(blockJson, classOf[Block])
      blockChain.blocks.addOne(block)
    })

    blockChain
  }
}
