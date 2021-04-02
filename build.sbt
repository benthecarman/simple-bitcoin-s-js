enablePlugins(ScalaJSPlugin)

name := "Scala.js Tutorial"
scalaVersion := "2.13.5" // or any other Scala version >= 2.11.12

// This is an application with a main method
scalaJSUseMainModuleInitializer := true

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Vector(
  "org.scala-js" %%% "scalajs-dom" % "1.1.0",
  "org.bitcoin-s" %%% "bitcoin-s-crypto" % "0.5.0-118-abcf78cf-SNAPSHOT" withSources() withJavadoc(),
  "org.bitcoin-s" %%% "bitcoin-s-core" % "0.5.0-118-abcf78cf-SNAPSHOT"  withSources() withJavadoc()
)