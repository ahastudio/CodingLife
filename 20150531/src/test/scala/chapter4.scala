import org.scalatest._

sealed trait Either[+E, +A] {
  def map[B](f: A => B): Either[E, B] = this match {
    case Left(a) => Left(a)
    case Right(a) => Right(f(a))
  }

  def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] = this match {
    case Left(a) => Left(a)
    case Right(a) => f(a)
  }

  def orElse[EE >: E, B >: A](b: => Either[EE, B]): Either[EE, B] = this match {
    case Left(a) => b
    case _ => this
  }

  def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] = {
    // for (x <- this; y <- b) yield f(x, y)
    flatMap(a => b.map(b => f(a, b)))
  }
}

case class Left[+E](value: E) extends Either[E, Nothing]
case class Right[+A](value: A) extends Either[Nothing, A]

object Either {
  def sequence[E, A](es: List[Either[E, A]]): Either[E, List[A]] = es match {
    case Nil => Right(Nil)
    case head :: tail =>
      // for (h <- head; t <- sequence(tail)) yield h :: t
      head.flatMap(h => sequence(tail).map(t => h :: t))
  }

  def traverse[E, A, B](as: List[A])
                       (f: A => Either[E, B]): Either[E, List[B]] = as match {
    case Nil => Right(Nil)
    case head :: tail =>
      // for (h <- f(head); t <- traverse(tail)(f)) yield h :: t
      f(head).flatMap(h => traverse(tail)(f).map(t => h :: t))
  }
}

class TestChapter4 extends FlatSpec with Matchers {
  def mean(xs: IndexedSeq[Double]): Either[String, Double] =
    if (xs.isEmpty)
      Left("mean of empty list!")
    else
      Right(xs.sum / xs.length)

  // 연습문제 4.6
  it should "have functions" in {
    mean(IndexedSeq(1.0, 3.0)).map(_ + 1) should be (Right(3.0))
    mean(IndexedSeq()).map(_ + 1) should be (Left("mean of empty list!"))

    Right(IndexedSeq(1.0, 3.0)).flatMap(mean) should be (Right(2.0))
    Left("that's no no").flatMap(mean) should be (Left("that's no no"))

    mean(IndexedSeq(1.0, 3.0)).orElse(Left("wtf")) should be (Right(2.0))
    mean(IndexedSeq()).orElse(Left("wtf")) should be (Left("wtf"))

    def add(a: Int, b: Int) = a + b

    Right(1).map2(Right(2))(add) should be (Right(3))
    Right(1).map2(Left("wtf 2"))(add) should be (Left("wtf 2"))
    Left("wtf 1").map2(Right(2))(add) should be (Left("wtf 1"))
    Left("wtf 1").map2(Left("wtf 2"))(add) should be (Left("wtf 1"))
  }

  // 연습문제 4.7
  it should "sequence and traverse a list" in {
    Either.sequence(List(Right(1), Right(2))) should be (Right(List(1, 2)))
    Either.sequence(List(Left("error1"), Right(2))) should be (Left("error1"))
    Either.sequence(List(Right(1), Left("error2"))) should be (Left("error2"))
    Either.sequence(List(Left("error1"), Left("error2"))) should
      be (Left("error1"))

    def double(a: Int) = if (a < 0) Left("negative: " + a) else Right(a * 2)

    Either.traverse(List(1, 2))(double) should be (Right(List(2, 4)))
    Either.traverse(List(-1, 2))(double) should be (Left("negative: -1"))
    Either.traverse(List(1, -2))(double) should be (Left("negative: -2"))
    Either.traverse(List(-1, -2))(double) should be (Left("negative: -1"))
  }
}
