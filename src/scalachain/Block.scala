package scalachain

class Block[Object](var data: Object) {
  var index = 0
  var timestamp = 0L
  var hash = ""
  var previousHash = ""
  var nonce = 0
}