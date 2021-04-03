package org.bitcoins.simple

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.scaladsl.{Sink, Source}

import java.nio.file.{Files, Path}
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}

object Server extends App {


  import akka.actor.ActorSystem
  import akka.http.scaladsl.Http
  import akka.http.scaladsl.model.HttpMethods._
  import akka.http.scaladsl.model._
  import akka.stream.scaladsl.Sink

  implicit val system = ActorSystem()
  implicit val executionContext = system.dispatcher

  val serverSource = Http().newServerAt("localhost", 8080).connectionSource()

  private val basePath = Path.of("/home","suredbits","dev","simple-bitcoin-s-js")
  val requestHandler: HttpRequest => HttpResponse = {
    case HttpRequest(GET, Uri.Path("/"), _, _, _) =>
      val path = basePath.resolve("scala-js-tutorial-fastopt.html")
      val bytes = Files.readAllBytes(path)
      HttpResponse(entity = HttpEntity(
        ContentTypes.`text/html(UTF-8)`,
        bytes))

    case HttpRequest(GET, Uri.Path("/main.js"), _, _, _) =>
      val path = basePath.resolve("target")
        .resolve("scala-2.13")
        .resolve("scala-js-tutorial-fastopt")
        .resolve("main.js")
      val bytes = Files.readAllBytes(path)

      val media = MediaTypes.`application/javascript`.withCharset(HttpCharsets.`UTF-8`)
      HttpResponse(entity = HttpEntity.apply(
        media,
        bytes))

    case HttpRequest(GET, Uri.Path("/bcrypto/lib/js/hash160.js”"), _, _, _) =>
      sys.error("BOOM!")

    case r: HttpRequest =>
      r.discardEntityBytes() // important to drain incoming HTTP Entity stream
      HttpResponse(404, entity = "Unknown resource!")
  }

  val bindingFuture: Future[Http.ServerBinding] =
    serverSource.to(Sink.foreach { connection =>
      println("Accepted new connection from " + connection.remoteAddress)

      connection.handleWithSyncHandler(requestHandler)
      // this is equivalent to
      // connection handleWith { Flow[HttpRequest] map requestHandler }
    }).run()

}
