import org.scalatest._

class TestSort extends FlatSpec with Matchers {

  def qsort[A](as: Seq[A])(f: (A, A) => Boolean): Seq[A] = as match {
    case Seq() => Seq()
    case Seq(a) => Seq(a)
    case pivot :: tail =>
      qsort(tail.filter(f(_, pivot)))(f) ++ Seq(pivot) ++
        qsort(tail.filter(!f(_, pivot)))(f)
  }

  def msort[A](as: Seq[A])(f: (A, A) => Boolean): Seq[A] = as match {
    case Seq() => Seq()
    case Seq(a) => Seq(a)
    case _ => {
      val half = as.length / 2
      val left = msort(as.take(half))(f)
      val right = msort(as.drop(half))(f)
      def merge(result: Seq[A], left: Seq[A], right: Seq[A]): Seq[A] = {
        if (left.isEmpty) {
          result ++ right
        } else if (right.isEmpty) {
          result ++ left
        } else if (f(left.head, right.head)) {
          merge(result ++ Seq(left.head), left.drop(1), right)
        } else {
          merge(result ++ Seq(right.head), left, right.drop(1))
        }
      }
      merge(Seq(), left, right)
    }
  }

  it should "sort using quick sort" in {
    qsort(Seq(1, 3, 5, 4, 2))(_ <= _) should be (Seq(1, 2, 3, 4, 5))
  }

  it should "sort using merge sort" in {
    msort(Seq(1, 3, 5, 4, 2))(_ <= _) should be (Seq(1, 2, 3, 4, 5))
  }
}
