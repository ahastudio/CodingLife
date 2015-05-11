import org.scalatest._

sealed trait Option[+A] {
  def map[B](f: A => B): Option[B] = this match {
    case None => None
    case Some(a) => Some(f(a))
  }

  def flatMap[B](f: A => Option[B]): Option[B] = this match {
    case None => None
    case Some(a) => f(a)
  }

  def flatMap2[B](f: A => Option[B]): Option[B] = {
    if (this == None) None
    else f(this.asInstanceOf[Some[A]].get)
  }

  def flatMap3[B](f: A => Option[B]): Option[B] = None

  def getOrElse[B >: A](default: => B): B = this match {
    case None => default
    case Some(a) => a
  }

  def orElse[B >: A](ob: => Option[B]): Option[B] = {
    if (this == None) ob
    else this
  }

  def filter(f: A => Boolean): Option[A] = None
}

case class Some[+A](get: A) extends Option[A] {
  override def flatMap3[B](f: A => Option[B]) = f(get)

  override def filter(f: A => Boolean) = if (f(get)) this else None
}

case object None extends Option[Nothing]

class TestChapter4 extends FlatSpec with Matchers {
  // 연습문제 4.1
  it should "run Option's functions" in {
    Some(3).map(_ * 2) should be (Some(6))
    None.map((x: Int) => x * 2) should be (None)

    Some(3).map(a => Some(a * 2)) should be (Some(Some(6)))

    Some(3).flatMap(a => Some(a * 2)) should be (Some(6))
    None.flatMap((a: Int) => Some(a * 2)) should be (None)

    def double(x: Int) = Some(x * 2)
    Some(3).flatMap2(double) should be (Some(6))
    None.flatMap2(double) should be (None)

    val option: Option[Int] = Some(3)
    option.flatMap2(double) should be (Some(6))

    Some(3).flatMap3(double) should be (Some(6))
    None.flatMap3(double) should be (None)
    option.flatMap3(double) should be (Some(6))

    Some(3).getOrElse(10) should be (3)
    None.getOrElse(10) should be (10)

    Some(3).orElse(Some(10)) should be (Some(3))
    None.orElse(Some(10)) should be (Some(10))

    Some(4).filter(_ % 2 == 0) should be (Some(4))
    Some(3).filter(_ % 2 == 0) should be (None)
    None.filter((x: Int) => x % 2 == 0) should be (None)
  }
}
