import org.scalatest._

sealed trait Stream[+A] {
  def headOption: Option[A] = this match {
    case Empty => None
    case Cons(h, t) => Some(h())
  }

  def toList: List[A] = this match {
    case Empty => Nil
    case Cons(h, t) => h() :: t().toList
  }
}

case object Empty extends Stream[Nothing]
case class Cons[+A](h: () => A, t: () => Stream[A]) extends Stream[A]

object Stream {
  def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = tl
    Cons(() => head, () => tail)
  }

  def empty[A]: Stream[A] = Empty

  def apply[A](as: A*): Stream[A] =
    if (as.isEmpty) empty
    else cons(as.head, apply(as.tail: _*))
}

class TestChapter5 extends FlatSpec with Matchers {
  it should "returns head as option" in {
    Stream.apply(1, 2, 3).headOption should be (Some(1))
    Stream.cons(1, Empty).headOption should be (Some(1))
    Stream.apply().headOption should be (None)
    Stream.empty.headOption should be (None)
  }

  // 연습문제 5.1
  it should "convert a stream to a list" in {
    Stream.apply(1, 2, 3).toList should be (List(1, 2, 3))
    Stream.cons(1, Empty).toList should be (List(1))
    Stream.apply().toList should be (List())
    Stream.empty.toList should be (List())
  }
}
