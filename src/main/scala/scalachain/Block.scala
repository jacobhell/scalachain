package scalachain

class Block (var string: String) {
  var index = 0
  var timestamp = 0L
  var hash = ""
  var previousHash = ""
  var nonce = 0
  var data = string
}