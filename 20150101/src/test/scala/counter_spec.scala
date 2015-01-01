import scala.math.pow
import org.scalatest._

object Counter {
  val unitPrice = 8.00

  def discount(books: List[Int]) = {
    List(0, 0, 0.05, 0.10, 0.20, 0.25)(books.sum)
  }

  def maskToList(mask: Int): List[Int] = {
    List.range(0, 5).map { case i => if ((mask & (1 << i)) > 0) 1 else 0 }
  }

  def getPrice(books: List[Int]): Double = {
    if (books.forall(_ <= 1))
      unitPrice * books.sum * (1 - discount(books))
    else {
      (1 until 1 << 5).map(maskToList).map { case set =>
        val remain = books.zip(set).map { case(a, b) => a - b }
        if (remain.forall(_ >= 0)) getPrice(set) + getPrice(remain)
        else 0
      }.filter(_ > 0).min
    }
  }
}

class CounterSpec extends FlatSpec with Matchers {
  it should "discount the price" in {
    Counter.getPrice(List(2, 2, 2, 1, 1)) should be (51.20)
    Counter.getPrice(List(0, 0, 0, 0, 0)) should be (0.00)
    Counter.getPrice(List(1, 0, 0, 0, 0)) should be (8.00)
    Counter.getPrice(List(1, 1, 0, 0, 0)) should be (15.20)
    Counter.getPrice(List(1, 1, 1, 0, 0)) should be (21.60)
    Counter.getPrice(List(1, 1, 1, 1, 0)) should be (25.60)
    Counter.getPrice(List(1, 1, 1, 1, 1)) should be (30.00)
    Counter.getPrice(List(2, 0, 0, 0, 0)) should be (16.00)
    Counter.getPrice(List(2, 1, 0, 0, 0)) should be (23.20)
  }
}
