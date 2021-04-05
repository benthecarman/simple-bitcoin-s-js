### Using bitcoin-s from scala-js 

This project is to provide a simple html page that displays bitcoin-s data structures.

The Scala file to modify to test things out is [BitcoinSExample](src/main/scala/org/bitcoins/simple/BitcoinSExample.scala)

> sbt fastOptJS::webpack

This will generate a javascript file located at `target/scala-2.13/scalajs-bundler/main/scala-js-tutorial-fastopt-bundle.js`

This javascript file is referenced inside of [`scala-js-tutorial-fastopt.html`](scala-js-tutorial-fastopt.html)


You can now start the webpage with your favorite web browser and see a private key, public key, scriptpubkey and bitcoin address
output on the webpage.

> firefox scala-js-tutorial-fastopt.html

![Alt text](Screenshot%20from%202021-04-05%2006-40-02.png)
