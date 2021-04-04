package org.bitcoins.simple
import org.bitcoins.core.config.TestNet3
import org.bitcoins.core.protocol.script.P2WPKHWitnessSPKV0
import org.bitcoins.crypto.ECPrivateKey
import org.scalajs.dom
import org.scalajs.dom.document
import scala.scalajs.js.annotation.JSExportTopLevel

object BitcoinSExample {

  def main(args: Array[String]): Unit = {
    appendPar(document.body, "Hello World")
    val privKey = ECPrivateKey.freshPrivateKey
    val publicKey =  privKey.publicKey
    val network = TestNet3
    val p2wpkh = P2WPKHWitnessSPKV0(publicKey)
    //val addr = P2PKHAddress.fromScriptPubKey(p2wpkh,network)
    appendPar(document.body, s"privKey=${privKey.hex}")
    appendPar(document.body, s"pubKey=${publicKey.hex}")
    appendPar(document.body, s"p2wpkh=${p2wpkh.asmBytes}")
    appendPar(document.body, s"network=$network")
    //(document.body,s"addr=${addr.toString()}")

  }

  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    parNode.textContent = text
    targetNode.appendChild(parNode)
  }

  @JSExportTopLevel("addClickedMessage")
  def addClickedMessage(): Unit = {
    appendPar(document.body, "Hello World")
    val privKey = ECPrivateKey.freshPrivateKey
    val publicKey =  privKey.publicKey
    val network = TestNet3
    val p2wpkh = P2WPKHWitnessSPKV0(publicKey)
    //val addr = P2PKHAddress(p2wpkh,network)
    appendPar(document.body, s"privKey=${privKey.hex}")
    appendPar(document.body, s"pubKey=${publicKey.hex}")
    appendPar(document.body, s"p2wpkh=${p2wpkh.asmBytes}")
    appendPar(document.body, s"network=$network")
    //appendPar(document.body,s"addr=${addr.toString()}")
  }

}
