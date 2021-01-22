# scalachain
Text Based Blockchain Implementation Written in Scala

Build:
```
  git clone https://github.com/jacobhell/scalachain
  cd scalachain
  sbt assembly
  
```
To use:

```
cd target/scala-2.13
java -jar scalachain.jar write "message1"
java -jar scalachain.jar write "message2"

java -jar scalachain.jar list
```

Output:
```
hash: 0b19c50767f7804054b8c0cbaaec1c4b75d1b16f2d09a906789cad8f9953ded5
nonce: 22
index: 0
timestamp: 1611353996467
data: message1

hash: 0a5dd6520248f35206c7fdf7d5cb20f16b4ecf0465b2a418bbdc0a2e0f4ef178
nonce: 8
index: 1
timestamp: 1611354802563
data: message2
```
