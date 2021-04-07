package org.bitcoins.simple

import org.bitcoins.core.currency.Satoshis
import org.bitcoins.core.protocol.dlc.{CallOption, RoundingIntervals}
import org.scalajs.dom
import org.scalajs.dom.html.Input
import org.scalajs.dom.{document, Element}

import scala.scalajs.js.annotation.JSExportTopLevel

object BitcoinSExample {

  def main(args: Array[String]): Unit = {
    appendPar(document.body, "DLC Templates")
    appendPar(document.body, "Call Option")

    val individualCollateral = document.createElement("label")
    individualCollateral.textContent = "Individual Collateral"
    val individualCollateralText =
      document.createElement("input").asInstanceOf[Input]
    document.body.appendChild(individualCollateral)
    document.body.appendChild(individualCollateralText)
    document.body.appendChild(document.createElement("br"))

    val totalCollateral = document.createElement("label")
    totalCollateral.textContent = "Total Collateral"
    val totalCollateralText = document.createElement("input")
    document.body.appendChild(totalCollateral)
    document.body.appendChild(totalCollateralText)
    document.body.appendChild(document.createElement("br"))

    val premium = document.createElement("label")
    premium.textContent = "Premium"
    val premiumText = document.createElement("input")
    document.body.appendChild(premium)
    document.body.appendChild(premiumText)
    document.body.appendChild(document.createElement("br"))

    val numDigits = document.createElement("label")
    numDigits.textContent = "Num Digits"
    val numDigitsText = document.createElement("input")
    document.body.appendChild(numDigits)
    document.body.appendChild(numDigitsText)
    document.body.appendChild(document.createElement("br"))

    val strikePrice = document.createElement("label")
    strikePrice.textContent = "Strike Price"
    val strikePriceText = document.createElement("input")
    document.body.appendChild(strikePrice)
    document.body.appendChild(strikePriceText)
    document.body.appendChild(document.createElement("br"))

    val resultArea = document.createElement("textarea")

    //add a button to add more keys/addresses on demand
    val create = document.createElement("button")
    create.textContent = "Create Descriptor"

    //when the button is clicked call the generateAddress function
    create.addEventListener(
      "click",
      { (_: dom.MouseEvent) =>
        generateDescriptor(
          individualCollateralText = individualCollateralText,
          totalCollateralText = totalCollateralText.asInstanceOf[Input],
          premiumText = premiumText.asInstanceOf[Input],
          numDigitsText = numDigitsText.asInstanceOf[Input],
          strikePriceText = strikePriceText.asInstanceOf[Input],
          resultArea = resultArea
        )
      }
    )

    //add the actual button to the page
    document.body.appendChild(create)
    document.body.appendChild(document.createElement("br"))
    document.body.appendChild(resultArea)
    ()
  }

  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    parNode.textContent = text
    targetNode.appendChild(parNode)
  }

  @JSExportTopLevel("generateDescriptor")
  def generateDescriptor(
      individualCollateralText: Input,
      totalCollateralText: Input,
      premiumText: Input,
      numDigitsText: Input,
      strikePriceText: Input,
      resultArea: Element): Unit = {
    println("generateDescriptor")
    val individualCollateral = Satoshis(individualCollateralText.value.toLong)
    val totalCollateral = Satoshis(totalCollateralText.value.toLong)
    val premium = Satoshis(premiumText.value.toLong)
    val numDigits = numDigitsText.value.toInt
    val strikePrice = strikePriceText.value.toLong

    val template: CallOption = CallOption(individualCollateral,
                              totalCollateral,
                              numDigits,
                              strikePrice,
                              premium,
                              RoundingIntervals.noRounding)

    println(template.toContractDescriptor.hex)

    resultArea.textContent = template.toContractDescriptor.hex
  }

}
