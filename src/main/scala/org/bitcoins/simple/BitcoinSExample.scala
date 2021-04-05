package org.bitcoins.simple
import org.bitcoins.core.config.TestNet3
import org.bitcoins.core.protocol.{Bech32Address, P2PKHAddress}
import org.bitcoins.core.protocol.script.P2WPKHWitnessSPKV0
import org.bitcoins.crypto.ECPrivateKey
import org.scalajs.dom
import org.scalajs.dom.document

import scala.scalajs.js.annotation.JSExportTopLevel

/**
 * This creates a simple single page webapp that loads private keys/bitcoin addreses
 * This script can be run with
 *
 * >fastOptJS::webpack
 *
 * This will generate a javascript file that is located at
 * target/scala-2.13/scalajs-bundler/main/scala-js-tutorial-fastopt-bundle.js
 *
 * This javascript file is referenced by the file scala-js-tutorial-fastopt.html
 *
 * After building the javascript file, you can open the html file with your
 * favorite web browser and see private keys and addresses generated
 *
 * This javascript file is linked inside of
 * Most of the structural code is cribbed from the scalajs tutorial
 *
 * @see http://www.scala-js.org/doc/tutorial/basic/
 */
object BitcoinSExample {

  def main(args: Array[String]): Unit = {
    appendPar(document.body, "Hello World")

    //load some keys/addresses on page load
    generateAddress()

    //add a button to add more keys/addresses on demand
    val button = document.createElement("button")
    button.textContent = "Click me to generate more keys/addresses!"

    //when the button is clicked call the generateAddress function
    button.addEventListener("click", { (e: dom.MouseEvent) =>
      generateAddress()
    })

    //add the actual button to the page
    document.body.appendChild(button)
    ()
  }

  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    parNode.textContent = text
    targetNode.appendChild(parNode)
  }

  @JSExportTopLevel("generateAddress")
  def generateAddress(): Unit = {
    val privKey = ECPrivateKey.freshPrivateKey
    val publicKey =  privKey.publicKey
    val network = TestNet3
    val p2wpkh = P2WPKHWitnessSPKV0(publicKey)
    val addr = Bech32Address.fromScriptPubKey(p2wpkh,network)
    appendPar(document.body, s"privKey=${privKey.hex}")
    appendPar(document.body, s"pubKey=${publicKey.hex}")
    appendPar(document.body, s"p2wpkh=${p2wpkh.asmBytes}")
    appendPar(document.body, s"network=$network")
    appendPar(document.body,s"addr=${addr.toString()}")
  }

}
