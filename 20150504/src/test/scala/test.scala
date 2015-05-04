import org.scalatest._

class TestFibonacciSequence extends FlatSpec with Matchers {
  def fib(n: Int): Int = {
    if (n < 2) Seq(0, 1)(n)
    else (2 to n).foldLeft((0, 1))((a, _) => (a._2, a._1 + a._2))._2
  }

  def fib2(n: Int): Int = {
    (1 to n).foldLeft((0, 1))((x, _) => x match {case (a, b) => (b, a + b)})._1
  }

  def fib3(n: Int): Int = n match {
    case 0 => 0
    case 1 => 1
    case n => (1 to n).foldLeft(List(0, 1))((a, _) => (a(0) + a(1))::a)(0)
  }

  it should "returns fibonacci sequence" in {
    for((x, i) <- List(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55).zipWithIndex) {
      fib(i) should be (x)
      fib2(i) should be (x)
      fib3(i) should be (x)
    }
  }
}
