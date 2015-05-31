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
    for (x <- this; y <- b) yield f(x, y)
  }
}

case class Left[+E](value: E) extends Either[E, Nothing]
case class Right[+A](value: A) extends Either[Nothing, A]

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
}
