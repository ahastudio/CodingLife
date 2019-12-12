import kotlinx.html.dom.append
import kotlinx.html.js.*
import kotlin.browser.document

fun main() {
    // console.log
    println("Hello, world!")

    // DOM
    document.getElementById("app")
            ?.also { it.innerHTML = "" }
            ?.append {
                h1 { +"Welcome" }
                p { +"Hello, world!" }
            }
}
