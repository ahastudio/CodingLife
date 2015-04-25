import org.scalatest._

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  // helpers

  @annotation.tailrec
  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = as match {
    case Nil => z
    case Cons(head, tail) => foldLeft(tail, f(z, head))(f)
  }

  def reverse[A](as: List[A]) =
    foldLeft(as, List[A]())((acc, a) => Cons(a, acc))

  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B) =
    foldLeft(reverse(as), z)((a, b) => f(b, a))

  def append[A](as: List[A], z: A) = foldRight(as, Cons(z, Nil))(Cons(_, _))

  def concat[A](lists: List[List[A]]) = {
    foldLeft(lists, List[A]())((a, b) => foldRight(a, b)(Cons(_, _)))
  }

  def concat2[A](lists: List[A]*) = {
    // lists is Seq.
    lists.foldLeft(List[A]())((a, b) => foldRight(a, b)(Cons(_, _)))
  }

  def increase(l: List[Int]) =
    foldRight(l, List[Int]())((a, b) => Cons(a + 1, b))

  def toStrings(l: List[Double]) =
    foldRight(l, List[String]())((a, b) => Cons(a.toString, b))

  def map[A, B](as: List[A])(f: A => B) =
    foldRight(as, List[B]())((a, b) => Cons(f(a), b))

  def filter[A](as: List[A])(f: A => Boolean) =
    foldRight(as, List[A]())((a, b) => if (f(a)) Cons(a, b) else b)

  def flatMap[A, B](as: List[A])(f: A => List[B]) =
    foldRight(as, List[B]()) { (a, b) => foldRight(f(a), b)(Cons(_, _)) }

  def filter2[A](as: List[A])(f: A => Boolean) =
    flatMap(as)(a => if (f(a)) List(a) else Nil)

  def plus(lhs: List[Int], rhs: List[Int]): List[Int] = (lhs, rhs) match {
    case (Cons(a, b), Cons(c, d)) => Cons(a + c, plus(b, d))
    case (_, _) => Nil
  }

  def zipWith[A, B](lhs: List[A], rhs: List[A])(f: (A, A) => B): List[B] =
    (lhs, rhs) match {
      case (Cons(a, b), Cons(c, d)) => Cons(f(a, c), zipWith(b, d)(f))
      case (_, _) => Nil
    }

  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean =
    (sup, sub) match {
      case (_, Nil) => true
      case (Nil, _) => false
      case (Cons(a, b), Cons(c, d)) =>
        def check[A](sup: List[A], sub: List[A]): Boolean = (sup, sub) match {
          case (_, Nil) => true
          case (Nil, _) => false
          case (Cons(a, b), Cons(c, d)) => (a == c) && check(b, d)
        }
        (a == c) && check(b, d) || hasSubsequence(b, sub)
    }
}

class TestChapter3 extends FlatSpec with Matchers {
  // 연습문제 3.15
  it should "merge lists" in {
    List.concat(List(List(1, 2, 3), List(4, 5, 6))) should
      be (List(1, 2, 3, 4, 5, 6))
    List.concat(List(List(1, 2), List(3, 4), List(5, 6))) should
      be (List(1, 2, 3, 4, 5, 6))

    List.concat2(List(1, 2, 3), List(4, 5, 6)) should
      be (List(1, 2, 3, 4, 5, 6))
    List.concat2(List(1, 2), List(3, 4), List(5, 6)) should
      be (List(1, 2, 3, 4, 5, 6))
  }

  // 연습문제 3.16
  it should "add 1 each element" in {
    List.increase(List(1, 2, 3, 4, 5)) should be (List(2, 3, 4, 5, 6))
  }

  // 연습문제 3.17
  it should "convert to string each element" in {
    List.toStrings(List(1, 2, 3)) should be (List("1.0", "2.0", "3.0"))
  }

  // 연습문제 3.18
  it should "do something each element" in {
    List.map(List(1, 2, 3))(_ * 2) should be (List(2, 4, 6))
  }

  // 연습문제 3.19
  it should "filter elements in list" in {
    List.filter(List(1, 2, 3, 4))(_ % 2 == 0) should be (List(2, 4))
  }

  // 연습문제 3.20
  it should "gather all elements into a list" in {
    List.flatMap(List(1, 2, 3))(i => List(i, i)) should
      be (List(1, 1, 2, 2, 3, 3))
    List.flatMap(List(1, 2, 3))(i => Nil) should be (Nil)
  }

  // 연습문제 3.21
  it should "filter using flatMap" in {
    List.filter2(List(1, 2, 3, 4))(_ % 2 == 0) should be (List(2, 4))
  }

  // 연습문제 3.22
  it should "add elements of two lists" in {
    List.plus(List(1, 2, 3), List(4, 5, 6)) should be (List(5, 7, 9))
  }

  // 연습문제 3.23
  it should "do something with elements of two lists" in {
    List.zipWith(List(1, 2, 3), List(4, 5, 6))(_ * _) should
      be (List(4, 10, 18))
  }

  // 연습문제 3.24
  it should "check it has sub-sequence" in {
    List.hasSubsequence(List(1, 2, 3, 4), List(1, 2)) should be (true)
    List.hasSubsequence(List(1, 2, 3, 4), List(2, 3)) should be (true)
    List.hasSubsequence(List(1, 2, 3, 4), List(4)) should be (true)
    List.hasSubsequence(List(1, 2, 3, 4), List(5)) should be (false)
    List.hasSubsequence(List(1, 2, 3, 4), List(4, 5)) should be (false)
    List.hasSubsequence(List(1, 2, 3, 4), List(2, 1)) should be (false)
    List.hasSubsequence(List(1, 2, 3, 4), List(1, 3)) should be (false)
  }
}
