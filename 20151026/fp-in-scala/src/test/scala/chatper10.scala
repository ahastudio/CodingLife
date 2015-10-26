import org.scalatest._
import org.scalacheck.Properties
import org.scalacheck.Gen
import org.scalacheck.Prop

trait Monoid[A] {
  def op(a1: A, a2: A): A
  def zero: A
}

trait Monoids {
  val intAddition: Monoid[Int] = new Monoid[Int] {
    def op(a: Int, b: Int): Int = a + b
    val zero: Int = 0
  }

  val intMultiplication: Monoid[Int] = new Monoid[Int] {
    def op(a: Int, b: Int): Int = a * b
    val zero: Int = 1
  }

  val booleanOr: Monoid[Boolean] = new Monoid[Boolean] {
    def op(a: Boolean, b: Boolean): Boolean = a || b
    val zero: Boolean = false
  }

  val booleanAnd: Monoid[Boolean] = new Monoid[Boolean] {
    def op(a: Boolean, b: Boolean): Boolean = a && b
    val zero: Boolean = true
  }

  def optionMonoid[A] = new Monoid[Option[A]] {
    def op(a1: Option[A], a2 : Option[A]) = a1.orElse(a2)
    val zero = None
  }

  def endoMonoid[A] = new Monoid[A => A] {
    def op(a1: A => A, a2: A => A) = a1 compose a2
    val zero = (a: A) => a
  }
}

class TestChapter10 extends FlatSpec with Matchers with Monoids {
  def checkMonoid[A](monoid: Monoid[A], a: A, b: A, c: A) = {
    monoid.op(monoid.op(a, b), c) should be (monoid.op(a, monoid.op(b, c)))
    for (x <- Seq(a, b, c)) {
      monoid.op(x, monoid.zero) should be (x)
      monoid.op(monoid.zero, x) should be (x)
    }
  }

  // 연습문제 10.1
  it should "create a monoid" in {
    checkMonoid(intAddition, 1, 2, 3)
    checkMonoid(intMultiplication, 1, 2, 3)

    for (a <- Seq(true, false); b <- Seq(true, false); c <- Seq(true, false)) {
      checkMonoid(booleanOr, a, b, c)
      checkMonoid(booleanAnd, a, b, c)
    }
  }

  // 연습문제 10.2
  it should "create monoid for Option" in {
    val values = Seq(None, Some(1), Some(2), Some(3))
    for (a <- values; b <- values; c <- values) {
      checkMonoid(optionMonoid[Int], a, b, c)
    }
  }

  // 연습문제 10.3
  it should "create enofunction monoid" in {
    val monoid = endoMonoid[Int]
    def f1 = (x: Int) => x * 2
    def f2 = (x: Int) => x + 3
    def f3 = (x: Int) => (x * 2) + 3
    val fs = Seq(f1, f2, f3)

    for (a <- fs; b <- fs; c <- fs; v <- Range(0, 10)) {
      monoid.op(monoid.op(a, b), c)(v) should
        be (monoid.op(a, monoid.op(b, c))(v))
      for (x <- Seq(a, b, c)) {
        monoid.op(x, monoid.zero)(v) should be (x(v))
        monoid.op(monoid.zero, x)(v) should be (x(v))
      }
    }
  }
}

object MonoidSpecification extends Properties("Monoid") with Monoids {
  def monoidLaws[A](m: Monoid[A], gen: Gen[A]) =
    Prop.forAll(gen, gen, gen) { (a, b, c) =>
      m.op(m.op(a, b), c) == m.op(a, m.op(b, c))
    } && Prop.forAll(gen) { a => m.op(a, m.zero) == a && m.op(m.zero, a) == a }

  property("intAddition") =
    monoidLaws(intAddition, Gen.choose(0, 100))
  property("intMultiplication") =
    monoidLaws(intMultiplication, Gen.choose(0, 100))
  property("booleanOr") =
    monoidLaws(booleanOr, Gen.choose(0, 1).map(_ == 0))
  property("booleanAnd") =
    monoidLaws(booleanAnd, Gen.oneOf(true, false))
  property("optionMonoid") =
    monoidLaws(optionMonoid[Int], Gen.oneOf(None, Some(1), Some(2)))

}
