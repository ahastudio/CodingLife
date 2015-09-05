enablePlugins(ScalaJSPlugin)

name := "Scala.js"

scalaVersion := "2.11.7"

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.8.0"
libraryDependencies += "be.doeraene" %%% "scalajs-jquery" % "0.8.0"

scalaJSStage in Global := FastOptStage

skip in packageJSDependencies := false
