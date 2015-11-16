import org.scalatest._

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

  def isSorted[A](v: IndexedSeq[A])(f: (A, A) => Boolean): Boolean = {
    val m = new Monoid[Seq[A]] {
      def op(a: Seq[A], b: Seq[A]) =
        if (a == null || b == null) null
        else if (a.isEmpty || b.isEmpty) a ++ b
        else if (f(a.last, b.head)) Seq(a.head, b.last)
        else null
      def zero = Nil
    }
    foldMapV(v, m)(Seq(_)) != null
  }

  def isSorted(v: IndexedSeq[Int]): Boolean = {
    isSorted[Int](v)(_ <= _)
  }
}

class TestChapter10 extends FlatSpec with Matchers with Monoids {
  // 연습문제 10.9

  it should "check sorted" in {
    isSorted(IndexedSeq(1, 2, 3)) should be (true)
    isSorted(IndexedSeq(1, 3, 2)) should be (false)
    isSorted(IndexedSeq(2, 1, 3)) should be (false)
    isSorted(IndexedSeq(3, 2, 1)) should be (false)
  }
}
