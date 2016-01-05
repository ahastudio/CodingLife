import org.scalatest._

trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}

trait Monad[F[_]] extends Functor[F] {
  def unit[A](a: => A): F[A]
  def flatMap[A, B](ma: F[A])(f: A => F[B]): F[B]

  def map[A, B](ma: F[A])(f: A => B): F[B] =
    flatMap(ma)(a => unit(f(a)))
  def map2[A, B, C](ma: F[A], mb: F[B])(f: (A, B) => C): F[C] =
    flatMap(ma)(a => map(mb)(b => f(a, b)))

  def sequence[A](lma: List[F[A]]): F[List[A]] = {
    lma.foldRight(unit(List[A]())) { (e, a) =>
      flatMap(a)(a => map(e)(_ :: a))
    }
  }

  def traverse[A, B](la: List[A])(f: A => F[B]): F[List[B]] = {
    la.foldRight(unit(List[B]())) { (e, a) =>
      flatMap(a)(a => map(f(e))(_ :: a))
    }
  }

  def replicateM[A](n: Int, ma: F[A]): F[List[A]] = {
    if (n == 0) unit(List[A]())
    else flatMap(replicateM(n - 1, ma))(as => map(ma)(_ :: as))
  }

  def replicateM2[A](n: Int, ma: F[A]): F[List[A]] = {
    flatMap(ma) { a =>
      (1 to n).foldRight(unit(List[A]()))((_, as) => map(as)(a :: _))
    }
  }
}

class TestChapter11 extends FlatSpec with Matchers {
  val m = new Monad[Option] {
    def unit[A](a: => A): Option[A] = Some(a)
    def flatMap[A, B](ma: Option[A])(f: A => Option[B]) = ma.flatMap(f)
  }

  // 연습문제 11.3

  it should "sequence & traverse" in {
    m.sequence(List(Some(7), Some(2))) should be (Some(List(7, 2)))
    m.sequence(List(Some(7), None)) should be (None)

    m.traverse(List(1, 2, 3))(a => Some(a * 2)) should be (Some(List(2, 4, 6)))
  }

  // 연습문제 11.4

  it should "replicate" in {
    m.replicateM(3, Some(4)) should be (Some(List(4, 4, 4)))
    m.replicateM2(3, Some(4)) should be (Some(List(4, 4, 4)))
  }
}
