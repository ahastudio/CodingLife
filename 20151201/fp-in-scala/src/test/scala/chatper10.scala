import org.scalatest._

trait Monoid[A] {
  def op(a1: A, a2: A): A
  def zero: A
}

sealed trait WC
case class Stub(chars: String) extends WC
case class Part(lStub: String, words: Int, rStub: String) extends WC

trait Monoids {
  def foldMapV[A, B](v: IndexedSeq[A], m: Monoid[B])(f: A => B): B = v match {
    case IndexedSeq(a) => f(a)
    case _ => {
      val half = v.length / 2
      m.op(foldMapV(v.take(half), m)(f), foldMapV(v.drop(half), m)(f))
    }
  }

  val wcMonoid = new Monoid[WC] {
    def op(a: WC, b: WC): WC = (a, b) match {
      case (Stub(a), Stub(b)) => Stub(a + b)
      case (Part(al, aw, ar), Stub(b)) => Part(al, aw, ar + b)
      case (Stub(a), Part(bl, bw, br)) => Part(a + bl, bw, br)
      case (Part(al, aw, ar), Part(bl, bw, br)) =>
        Part(al, aw + bw + Seq(ar + bl).filter(_.trim.nonEmpty).length, br)
    }
    def zero: WC = Stub("")
  }

  def countWords(chars: String) = {
    if (chars.trim.isEmpty) 0
    else {
      val size = 5
      val stubs = (0 to chars.length / size)
        .map(i => chars.drop(i * size).take(size))
        .filter(_.nonEmpty)
        .map(Stub(_))
      def f(stub: Stub): WC = {
        if (!stub.chars.contains(' ')) stub
        else {
          val words = stub.chars.trim.split("\\s+")
          val Pattern = "(.)(.*)(.)".r
          stub.chars match {
            case Pattern(" ", _, " ") => Part("", words.length, "")
            case Pattern(_, _, " ") => Part(words.head, words.length - 1, "")
            case Pattern(" ", _, _) => Part("", words.length - 1, words.last)
            case Pattern(_, _, _) =>
              Part(words.head, words.length - 2, words.last)
            case _ => Part("", words.length, "")
          }
        }
      }
      foldMapV(stubs, wcMonoid)(f) match {
        case Part(a, words, b) => words + Seq(a, b).filter(_.nonEmpty).length
        case Stub(_) => 1
        case _ => 0
      }
    }
  }
}

case class IntMonoid() extends Monoid[Int] {
  def op(a1: Int, a2: Int): Int = a1 + a2
  def zero: Int = 0
}

trait Foldable[F[_]] {
  def foldRight[A, B](as: F[A])(z: B)(f: (A, B) => B): B
  def foldLeft[A, B](as: F[A])(z: B)(f: (B, A) => B): B
  def foldMap[A, B](as: F[A])(f: A => B)(mb: Monoid[B]): B
  def concatenate[A](as: F[A])(m: Monoid[A]): A = foldLeft(as)(m.zero)(m.op)
}

case class FoldableList() extends Foldable[List] {
  def foldRight[A, B](as: List[A])(z: B)(f: (A, B) => B): B = {
    as.foldRight(z)(f)
  }

  def foldLeft[A, B](as: List[A])(z: B)(f: (B, A) => B): B = {
    as.foldLeft(z)(f)
  }

  def foldMap[A, B](as: List[A])(f: A => B)(mb: Monoid[B]): B = as match {
    case Nil => mb.zero
    case h :: t => mb.op(f(h), foldMap(t)(f)(mb))
  }

  def foldMap2[A, B](as: List[A])(f: A => B)(mb: Monoid[B]): B =
    concatenate(as.map(f))(mb)
}

case class FoldableIndexedSeq() extends Foldable[IndexedSeq] {
  def foldRight[A, B](as: IndexedSeq[A])(z: B)(f: (A, B) => B): B =
    as.foldRight(z)(f)

  def foldLeft[A, B](as: IndexedSeq[A])(z: B)(f: (B, A) => B): B =
    as.foldLeft(z)(f)

  def foldMap[A, B](as: IndexedSeq[A])(f: A => B)(mb: Monoid[B]): B =
    concatenate(as.map(f))(mb)
}

class TestChapter10 extends FlatSpec with Matchers with Monoids {
  // 연습문제 10.12

  it should "be foldable" in {
    val m = new IntMonoid()

    FoldableList().foldLeft(List(1, 2, 3))(0)(_ + _) should be (6)
    FoldableList().foldRight(List(1, 2, 3))(0)(_ + _) should be (6)

    FoldableList().foldMap(List("1", "2", "3"))(_.toInt)(m) should be (6)
    FoldableList().concatenate(List(1, 2, 3))(m) should be (6)
    FoldableList().foldMap2(List("1", "2", "3"))(_.toInt)(m) should be (6)

    FoldableIndexedSeq().foldLeft(IndexedSeq(1, 2, 3))(0)(_ + _) should be (6)
    FoldableIndexedSeq().foldRight(IndexedSeq(1, 2, 3))(0)(_ + _) should be (6)
    FoldableIndexedSeq().foldMap(IndexedSeq("1", "2", "3"))(_.toInt)(m) should
      be (6)
  }
}
