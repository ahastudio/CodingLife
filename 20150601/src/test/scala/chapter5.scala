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

  def take(n: Int): Stream[A] = this match {
    case Cons(h, t) if (n > 0) => Cons(h, () => t().take(n - 1))
    case _ => Empty
  }

  def drop(n: Int): Stream[A] = this match {
    case Cons(h, t) if (n > 0) => t().drop(n - 1)
    case _ => this
  }

  def takeWhile(p: A => Boolean): Stream[A] = this match {
    case Cons(h, t) if (p(h())) => Cons(h, () => t().takeWhile(p))
    case _ => Empty
  }

  def foldRight[B](z: => B)(f: (A, => B) => B): B = this match {
    case Cons(h, t) => f(h(), t().foldRight(z)(f))
    case _ => z
  }

  def exists(p: A => Boolean): Boolean = foldRight(false)((a, b) => p(a) || b)

  def forAll(p: A => Boolean): Boolean = foldRight(true)((a, b) => p(a) && b)

  def takeWhile2(p: A => Boolean): Stream[A] =
    foldRight[Stream[A]](Empty) { (a, b) =>
      if (p(a)) Cons(() => a, () => b)
      else Empty
    }

  def headOption2: Option[A] = foldRight[Option[A]](None)((a, b) => Some(a))
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
  it should "return the head as option" in {
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

  // 연습문제 5.2
  it should "take and drop" in {
    Stream.apply(1, 2, 3).take(2).toList should be (List(1, 2))
    Stream.apply(1, 2, 3).take(5).toList should be (List(1, 2, 3))
    Stream.apply(1, 2, 3).take(-1).toList should be (List())

    Stream.apply(1, 2, 3).drop(1).toList should be (List(2, 3))
    Stream.apply(1, 2, 3).drop(2).toList should be (List(3))
    Stream.apply(1, 2, 3).drop(5).toList should be (List())
    Stream.apply(1, 2, 3).drop(-1).toList should be (List(1, 2, 3))

    Stream.apply(1, 2, 3).drop(1).take(1).toList should be (List(2))
  }

  // 연습문제 5.3
  it should "take while" in {
    Stream.apply(1, 2, 3).takeWhile(_ < 1).toList should be (List())
    Stream.apply(1, 2, 3).takeWhile(_ < 2).toList should be (List(1))
    Stream.apply(1, 2, 3).takeWhile(_ < 3).toList should be (List(1, 2))
    Stream.apply(1, 2, 3).takeWhile(_ < 4).toList should be (List(1, 2, 3))
  }

  it should "check exist" in {
    Stream.apply(1, 2, 3).exists(_ % 2 == 0) should be (true)
    Stream.apply(1, 3, 5).exists(_ % 2 == 0) should be (false)
    Stream.apply(2, 4, 6).exists(_ % 2 == 0) should be (true)
  }

  // 연습문제 5.4
  it should "check all" in {
    Stream.apply(1, 2, 3).forAll(_ < 10) should be (true)
    Stream.apply(1, 2, 3).forAll(_ > 10) should be (false)
  }

  // 연습문제 5.5
  it should "take while using foldRight" in {
    Stream.apply(1, 2, 3).takeWhile2(_ < 3).toList should be (List(1, 2))
    Stream.apply(1, 2, 3, 1).takeWhile2(_ < 3).toList should be (List(1, 2))
  }

  // 연습문제 5.6
  it should "get head using foldRight" in {
    Stream.apply(1, 2, 3).headOption2 should be (Some(1))
    Stream.apply().headOption2 should be (None)
  }
}
