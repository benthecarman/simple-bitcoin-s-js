enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)

name := "Scala.js Tutorial"
scalaVersion := "2.13.5" // or any other Scala version >= 2.11.12

// This is an application with a main method
scalaJSUseMainModuleInitializer := true

resolvers += Resolver.sonatypeRepo("snapshots")

fork in run := true

val bitcoinsV = "0.5.0-120-4ed87319-SNAPSHOT"
libraryDependencies ++= Vector(
  "org.scala-js" %%% "scalajs-dom" % "1.1.0",
  "org.bitcoin-s" %%% "bitcoin-s-crypto" % bitcoinsV withSources() withJavadoc(),
  "org.bitcoin-s" %%% "bitcoin-s-core" % bitcoinsV  withSources() withJavadoc(),
  "com.typesafe.akka" %% "akka-actor" % "2.6.10" withSources () withJavadoc (),
  "com.typesafe.akka" %% "akka-stream" % "2.6.10" withSources () withJavadoc (),
  "com.typesafe.akka" %% "akka-http" % "10.2.4" withSources () withJavadoc ()
)

/*
Compile / npmDependencies ++= Vector(
  "bcrypto" -> "5.4.0"
)
*/

//webpackConfigFile := Some(baseDirectory.value / "webpack.config.js")
