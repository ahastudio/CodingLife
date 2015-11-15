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
    val m = new Monoid[IndexedSeq[A]] {
      def op(a: IndexedSeq[A], b: IndexedSeq[A]) =
        if (a.isEmpty) b
        else if (b.isEmpty) a
        else if (f(a.last, b.head)) a ++ b
        else IndexedSeq()
      def zero = IndexedSeq()
    }
    foldMapV(v, m)(IndexedSeq(_)).length == v.length
  }
}

class TestChapter10 extends FlatSpec with Matchers with Monoids {
  it should "check sorted" in {
    isSorted(IndexedSeq(1, 2, 3))(_ <= _) should be (true)
    isSorted(IndexedSeq(1, 3, 2))(_ <= _) should be (false)
    isSorted(IndexedSeq(2, 1, 3))(_ <= _) should be (false)
    isSorted(IndexedSeq(3, 2, 1))(_ <= _) should be (false)
  }
}
