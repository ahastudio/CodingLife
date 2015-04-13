import org.scalatest._

class TestChapter2 extends FlatSpec with Matchers {
  def multiOrDiv(a: Int, b: Boolean): Double = if (b) a * 1.5 else a / 2.0

  def curry[A, B, C](f: (A, B) => C): A => (B => C) =
    (a: A) => (b: B) => f(a, b)

  def uncurry[A, B, C](f: A => B => C): (A, B) => C =
    (a: A, b: B) => f(a)(b)

  def double(x: Int) = x * 2
  def increase(x: Int) = x + 1

  def compose[A, B, C](f: B => C, g: A => B): A => C =
    (a: A) => f(g(a))

  // ----

  it should "solve 2.3" in {
    multiOrDiv(3, true) should be (4.5)
    multiOrDiv(3, false) should be (1.5)

    val f = curry(multiOrDiv)(3)
    f(true) should be (4.5)
    f(false) should be (1.5)
  }

  it should "solve 2.4" in {
    val f = uncurry(curry(multiOrDiv))
    f(3, true) should be (multiOrDiv(3, true))
    f(3, false) should be (multiOrDiv(3, false))
  }

  it should "solve 2.5" in {
    val f1 = compose(double, increase)
    f1(3) should be (double(increase(3)))

    val f2 = compose(increase, double)
    f2(3) should be (increase(double(3)))
  }
}
