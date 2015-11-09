import org.scalatest._
import org.scalacheck.Properties
import org.scalacheck.Gen
import org.scalacheck.Prop

trait Monoid[A] {
  def op(a1: A, a2: A): A
  def zero: A
}

trait Monoids {
  def foldMapV[A, B](v: IndexedSeq[A], m: Monoid[B])(f: A => B): B = v match {
    case IndexedSeq(a) => f(a)
    case _ => {
      val half = v.length / 2
      m.op(foldMapV(v.take(half), m)(f), foldMapV(v.drop(half), m)(f))
    }
  }
}

class TestChapter10 extends FlatSpec with Matchers with Monoids {
  // 연습문제 10.7

  it should "foldMap by balanced fold" in {
    val m = new Monoid[String] {
      def op(a: String, b: String) = a + b
      def zero = ""
    }
    foldMapV(IndexedSeq(1, 2, 3), m)(_.toString) should be ("123")
  }
}
