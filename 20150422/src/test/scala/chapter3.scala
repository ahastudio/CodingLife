import org.scalatest._

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  // helpers

  def dropWhile[A](as: List[A])(f: A => Boolean): List[A] = as match {
    case Cons(h, t) if (f(h)) => dropWhile(t)(f)
    case _ => as
  }

  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = as match {
    case Nil => z
    case Cons(x, xs) => f(x, foldRight(xs, z)(f))
  }

  def length[A](as: List[A]): Int = foldRight(as, 0)((_, a) => a + 1)

  @annotation.tailrec
  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = as match {
    case Nil => z
    case Cons(head, tail) => foldLeft(tail, f(z, head))(f)
  }
}

class TestChapter3 extends FlatSpec with Matchers {
  it should "drop heads of list with condition" in {
    List.dropWhile(List(1, 2, 3, 4, 5))(_ < 4) should be (List(4, 5))
  }

  it should "tests foldRight & List" in {
    List.foldRight(List(1, 2, 3), Nil:List[Int])(Cons(_, _)) should
      be (List(1, 2, 3))
  }

  it should "get length of list" in {
    List.length(List(1, 2, 3, 4, 5)) should be (5)
  }

  it should "calcualte sum of list using foldLeft" in {
    def add(a: Int, b: Int) = a + b
    List.foldLeft(List(1, 2, 3, 4, 5), 0)(_ + _) should be (15)
  }
}
