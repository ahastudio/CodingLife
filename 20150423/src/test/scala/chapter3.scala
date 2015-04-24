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

  def sum(as: List[Int]) = foldLeft(as, 0)((acc, a) => acc + a)

  def product(as: List[Double]) = foldLeft(as, 1.0)((acc, a) => acc * a)

  def length[A](as: List[A]) = foldLeft(as, 0)((acc, _) => acc + 1)

  def reverse[A](as: List[A]) =
    foldLeft(as, List[A]())((acc, a) => Cons(a, acc))

  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B) =
    foldLeft(reverse(as), z)((a, b) => f(b, a))

  def append[A](as: List[A], z: A) = foldRight(as, Cons(z, Nil))(Cons(_, _))
}

class TestChapter3 extends FlatSpec with Matchers {
  it should "get sum of list" in {
    List.sum(List(1, 2, 3, 4, 5)) should be (15)
  }

  it should "get product of list" in {
    List.product(List(1, 2, 3, 4, 5)) should be (120)
    List.product(List(1, 0, 3, 4, 5)) should be (0)
  }

  it should "get length of list" in {
    List.length(List(1, 2, 3, 4, 5)) should be (5)
  }

  it should "get reverse of list" in {
    List.reverse(List(1, 2, 3, 4, 5)) should be (List(5, 4, 3, 2, 1))
  }

  it should "foldRight using foldLeft" in {
    List.foldRight(List(10, 5, 3), 1)(_ - _) should be (7)
  }

  it should "append element to a list" in {
    List.append(List(1, 2, 3, 4, 5), 6) should be (List(1, 2, 3, 4, 5, 6))
  }
}
