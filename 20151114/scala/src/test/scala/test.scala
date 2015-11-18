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
      def merge(result: Seq[A], left: Seq[A], right: Seq[A]): Seq[A] =
        (left, right) match {
          case (Nil, _) => result ++ right
          case (_, Nil) => result ++ left
          case (lh::lt, rh::rt) =>
            if (f(lh, rh)) merge(result ++ Seq(lh), lt, right)
            else           merge(result ++ Seq(rh), left, rt)
        }
      val half = as.length / 2
      merge(Seq(), msort(as.take(half))(f), msort(as.drop(half))(f))
    }
  }

  it should "sort using quick sort" in {
    qsort(Seq(1, 3, 5, 4, 2))(_ <= _) should be (Seq(1, 2, 3, 4, 5))
  }

  it should "sort using merge sort" in {
    msort(Seq(1, 3, 5, 4, 2))(_ <= _) should be (Seq(1, 2, 3, 4, 5))
  }
}
