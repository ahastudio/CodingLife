import org.scalatest._
import org.scalacheck.Properties
import org.scalacheck.Gen
import org.scalacheck.Prop

trait Monoid[A] {
  def op(a1: A, a2: A): A
  def zero: A
}

trait Monoids {
  def endoMonoid[A] = new Monoid[A => A] {
    def op(a1: A => A, a2: A => A) = a1 compose a2
    val zero = (a: A) => a
  }

  def foldMap[A, B](as: List[A], m: Monoid[B])(f: A => B): B = {
    as.foldLeft(m.zero)((a, e) => m.op(a, f(e)))
  }

  def foldLeft[A](as: List[A], z: A)(f: (A, A) => A): A = {
    val m = new Monoid[A => A] {
      def op(a: A => A, b: A => A) = x => b(a(x))
      def zero = x => x
    }
    foldMap(as, m)(a => x => f(x, a))(z)
  }

  def foldRight[A](as: List[A], z: A)(f: (A, A) => A): A = {
    val m = new Monoid[A => A] {
      def op(a: A => A, b: A => A) = x => a(b(x))
      def zero = x => x
    }
    foldMap(as, m)(a => x => f(a, x))(z)
  }

  def foldRight2[A](as: List[A], z: A)(f: (A, A) => A): A = {
    def curry[A, B, C](f: (A, B) => C): A => (B => C) = a => b => f(a, b)
    foldMap(as, endoMonoid[A])(a => curry(f)(a))(z)
  }
}

class TestChapter10 extends FlatSpec with Matchers with Monoids {
  // 연습문제 10.5

  it should "foldMap" in {
    val m = new Monoid[String] {
      def op(a: String, b: String): String = a + b
      val zero: String = ""
    }
    foldMap(List(1, 2, 3), m)(_.toString) should be ("123")
  }

  // 연습문제 10.6

  it should "foldLeft and foldRight using foldMap" in {
    foldLeft(List(1, 2, 3), 0)(_ + _) should be (6)
    foldLeft(List(1, 2, 3), 0)(_ - _) should be (-6)
    foldLeft(List(1, 2, 3), 4)(_ * _) should be (24)

    List(1, 2, 3).foldLeft("-")(_ + _) should be ("-123")
    foldLeft(List(1, 2, 3), "-")(_ + _.toString) should be ("-123")

    List(1, 2, 3).foldRight(0)(_ + _) should be (6)
    foldRight(List(1, 2, 3), 0)(_ + _) should be (6)

    List(1, 2, 3).foldRight(10)((e, a) => a - e) should be (4)
    foldRight(List(1, 2, 3), 10)((e, a) => a - e) should be (4)

    List(1, 2, 3).foldRight("-")((e, a) => a + e.toString) should be ("-321")
    foldRight(List(1, 2, 3), "-")((e, a) => a + e.toString) should be ("-321")
    foldRight2(List(1, 2, 3), "-")((e, a) => a + e.toString) should be ("-321")
  }
}
