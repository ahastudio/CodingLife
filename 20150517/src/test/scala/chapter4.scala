import org.scalatest._

class TestChapter4 extends FlatSpec with Matchers {
  def average(xs: Seq[Double]): Option[Double] = xs match {
    case Nil => None
    case _ => Some(xs.sum / xs.length)
  }

  def variance(xs: Seq[Double]): Option[Double] = {
    average(xs).flatMap(m => average(xs.map(x => math.pow(x - m, 2))))
  }

  def lift[A, B](f: A => B): Option[A] => Option[B] = _ map f

  def Try[A](a: => A):Option[A] =
    try Some(a)
    catch { case e: Exception => None }

  def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = {
    a.flatMap(a => b.map(b => f(a, b)))
  }

  def sequence[A](a: List[Option[A]]): Option[List[A]] = a match {
    case Nil => Some(Nil)
    case head :: tail =>
      head.flatMap(head => sequence(tail).map(tail => head :: tail))
  }

  def parseInts(a: List[String]): Option[List[Int]] =
    sequence(a.map(x => Try(x.toInt)))

  def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] =
    a match {
      case Nil => Some(Nil)
      case head :: tail =>
        f(head).flatMap(head => traverse(tail)(f).map(tail => head :: tail))
    }

  def sequence2[A](a: List[Option[A]]): Option[List[A]] = traverse(a)(x => x)

  def parseInts2(a: List[String]): Option[List[Int]] =
    sequence2(a.map(x => Try(x.toInt)))

  def map3[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] =
    for (i <- a; j <- b) yield(f(i, j))

  def traverse2[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] =
    a match {
      case Nil => Some(Nil)
      case head :: tail =>
        for (i <- f(head); j <- traverse(tail)(f)) yield(i :: j)
    }

  def traverse3[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] =
    a match {
      case Nil => Some(Nil)
      case head :: tail => map3(f(head), traverse(tail)(f))(_ :: _)
    }

  def traverse4[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] =
    a.foldRight[Option[List[B]]](Some(Nil)) { (head, tail) =>
      map3(f(head), tail)(_ :: _)
    }

  // 연습문제 4.2
  it should "calculate variance" in {
    variance(Seq(2, 2, 2)) should be (Some(0.0))
    variance(Seq(2, 4, 6, 8)) should be (Some(5.0))
    variance(Seq()) should be (None)
  }

  it should "lift a normal function to a option function" in {
    def double(x: Int) = x * 2
    lift(double)(Some(2)) should be (Some(4))
  }

  // 연습문제 4.3
  it should "map two values with option" in {
    def add(a: Int, b: Int) = a + b

    map2(Some(12), Some(3))(add) should be (Some(15))
    map2(None, Some(3))(add) should be (None)
    map2(Some(12), None)(add) should be (None)
    map2(None, None)(add) should be (None)

    map2(Try("12".toInt), Try("3".toInt))(add) should be (Some(15))
    map2(Try("X".toInt), Try("3".toInt))(add) should be (None)
    map2(Try("12".toInt), Try("X".toInt))(add) should be (None)
    map2(Try("X".toInt), Try("X".toInt))(add) should be (None)
  }

  // 연습문제 4.4
  it should "filter it has any None" in {
    sequence(List(Some(1), Some(2))) should be (Some(List(1, 2)))
    sequence(List(None, Some(2))) should be (None)
    sequence(List(Some(1), None)) should be (None)
    sequence(List(None, None)) should be (None)

    parseInts(List("1", "2")) should be (Some(List(1, 2)))
    parseInts(List("X", "2")) should be (None)
    parseInts(List("1", "X")) should be (None)
    parseInts(List("X", "X")) should be (None)
  }

  // 연습문제 4.5
  it should "traverse option list" in {
    def double(x: Int) = Some(x * 2)
    traverse(List(1, 2))(double) should be (Some(List(2, 4)))

    sequence2(List(Some(1), Some(2))) should be (Some(List(1, 2)))
    sequence2(List(None, Some(2))) should be (None)
    sequence2(List(Some(1), None)) should be (None)
    sequence2(List(None, None)) should be (None)

    parseInts2(List("1", "2")) should be (Some(List(1, 2)))
    parseInts2(List("X", "2")) should be (None)
    parseInts2(List("1", "X")) should be (None)
    parseInts2(List("X", "X")) should be (None)
  }

  it should "map two values with option using for-comprehension" in {
    def add(a: Int, b: Int) = a + b

    map3(Some(12), Some(3))(add) should be (Some(15))
    map3(None, Some(3))(add) should be (None)
    map3(Some(12), None)(add) should be (None)
    map3(None, None)(add) should be (None)
  }

  it should "traverse option list using for-comprehension" in {
    def double(x: Int) = Some(x * 2)
    traverse2(List(1, 2))(double) should be (Some(List(2, 4)))
    traverse3(List(1, 2))(double) should be (Some(List(2, 4)))
    traverse4(List(1, 2))(double) should be (Some(List(2, 4)))
  }
}
