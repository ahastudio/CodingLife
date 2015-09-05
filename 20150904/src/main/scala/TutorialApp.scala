package tutorial.webapp

import scala.scalajs.js
import js.annotation.JSExport

import org.scalajs.dom
import dom.document

import org.scalajs.jquery.jQuery

object TutorialApp extends js.JSApp {
  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    val textNode = document.createTextNode(text)
    parNode.appendChild(textNode)
    targetNode.appendChild(parNode)
  }

  @JSExport
  def addClickedMessage(): Unit = {
    appendPar(document.body, "You clicked the button!")
  }

  def setupUI(): Unit = {
    jQuery("#click-me-button").click(addClickedMessage _)
    jQuery("body").append("<p>Hello World</p>")
  }

  def main(): Unit = {
    println("Hello world!")
    appendPar(document.body, "Hello World")
    jQuery("body").append("<p>[message]</p>")
    jQuery(setupUI _)
  }
}
