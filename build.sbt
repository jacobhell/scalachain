import sbt.project
import scala.language.postfixOps

ThisBuild / scalaVersion     := "2.13.4"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.jacobhell"
ThisBuild / organizationName := "jacobhell"
ThisBuild / useCoursier := false

val log4jVersion = "2.14.0"
libraryDependencies += "org.apache.logging.log4j" % "log4j-slf4j-impl" % log4jVersion
libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % log4jVersion
libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % log4jVersion
libraryDependencies += "org.apache.logging.log4j" % "log4j" % log4jVersion
libraryDependencies += "com.google.code.gson" % "gson" % "2.8.6"