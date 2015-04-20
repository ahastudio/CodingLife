import org.scalatest._

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  // helpers

  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  def tail[A](list: List[A]) = list match {
    case Nil => Nil
    case Cons(_, tail) => tail
  }

  def setHead[A](list: List[A], head: A) = list match {
    case Nil => Nil
    case Cons(_, tail) => Cons(head, tail)
  }

  def setHead2[A](list: List[A], head: A) = Cons(head, List.tail(list))

  def drop[A](l: List[A], n: Int): List[A] = {
    if (n <= 0) l
    else List.drop(List.tail(l), n - 1)
  }

  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
    case Nil => Nil
    case Cons(head, tail) =>
      if (f(head)) List.dropWhile(tail , f)
      else l
  }

  def init[A](l: List[A]): List[A] = l match {
    case Nil => Nil
    case Cons(_, Nil) => Nil
    case Cons(head, tail) => Cons(head, List.init(tail))
  }
}

class TestChapter3 extends FlatSpec with Matchers {
  it should "return Nil when it is empty" in {
    List() should be (Nil)
  }

  it should "get sum of list" in {
    List.sum(List(1, 2, 3, 4, 5)) should be (15)
  }

  it should "get product of list" in {
    List.product(List(1, 2, 3, 4, 5)) should be (120)
  }

  it should "get tail of list" in {
    List.tail(List(1, 2, 3, 4, 5)) should be (List(2, 3, 4, 5))
  }

  it should "replace head of list" in {
    List.setHead(List(1, 2, 3, 4, 5), 10) should be (List(10, 2, 3, 4, 5))
    List.setHead2(List(1, 2, 3, 4, 5), 10) should be (List(10, 2, 3, 4, 5))
  }

  it should "drop heads of list" in {
    List.drop(List(1, 2, 3, 4, 5), 2) should be (List(3, 4, 5))
    List.drop(List(1, 2, 3, 4, 5), 4) should be (List(5))
    List.drop(List(1, 2, 3, 4, 5), 100) should be (Nil)
    List.drop(List(1, 2, 3, 4, 5), -1) should be (List(1, 2, 3, 4, 5))

    def small(x: Int) = x < 4
    List.dropWhile(List(1, 2, 3, 4, 5), small) should be (List(4, 5))

    List.dropWhile(List(1, 2, 3, 4, 5), (x: Int) => x < 4) should be (List(4, 5))
    List.dropWhile(List(1, 2, 3, 4, 5), (x: Int) => x > 0) should be (Nil)
  }

  it should "remove last of list" in {
    List.init(List(1, 2, 3, 4, 5)) should be (List(1, 2, 3, 4))
  }
}
