import org.scalatest._

object Prop {
  type SuccessCount = Int
  type FailedCase = String
}

trait Prop {
  def check: Either[(Prop.FailedCase, Prop.SuccessCount), Prop.SuccessCount]

  def &&(p: Prop): Prop = (check, p.check) match {
    case (Right(a), Right(b)) => RightProp(a + b)
    case (Right(a), Left((m, b))) => LeftProp(m, a)
    case (Left((m, a)), Right(b)) => LeftProp(m, b)
    case (Left((m, a)), Left((_, b))) => LeftProp(m, 0)
  }
}

case class RightProp(count: Int) extends Prop {
  override def check = Right(count)
}

case class LeftProp(message: String, count: Int = 0) extends Prop {
  override def check = Left((message, count))
}

//

case class State[S, +A](run: S => (A, S))

trait RNG {
  def nextInt: (Int, RNG)
}

case class SimpleRNG(seed: Long) extends RNG {
  def nextInt: (Int, RNG) = {
    val newSeed = (seed * 37) & 0xFFFFFFFFFFFFL
    val nextRNG = SimpleRNG(newSeed)
    val n = (newSeed >>> 1).toInt
    (n, nextRNG)
  }
}

//

case class Gen[A](sample: State[RNG, A])

object Gen {
  def choose(start: Int, stopExclusive: Int): Gen[Int] = {
    def randomInt(rng: RNG): (Int, RNG) = {
      val (value, nextRNG) = rng.nextInt
      val range = stopExclusive - start
      (value % range + start, nextRNG)
    }
    Gen(State(randomInt))
  }

  def unit[A](a: A): Gen[A] = {
    val value = a
    Gen(State((rng: RNG) => (value, rng)))
  }

  def boolean: Gen[Boolean] = {
    def randomBoolean(rng: RNG): (Boolean, RNG) = {
      val (value, nextRNG) = rng.nextInt
      (value % 2 != 0, nextRNG)
    }
    Gen(State(randomBoolean))
  }

  def listOfN[A](n: Int, g: Gen[A]): Gen[List[A]] = {
    def randomList(rng: RNG): (List[A], RNG) = {
      (1 to n).foldLeft((List[A](), rng)) { (a, e) =>
        val (list, rng) = a
        val (value, nextRNG) = g.sample.run(rng)
        (list :+ value, nextRNG)
      }
    }
    Gen(State(randomList))
  }
}

class TestChapter8 extends FlatSpec with Matchers {
  // def rng(a: Int) = new RNG { override def nextInt = (a, this) }

  def rng(as: Int*): RNG = {
    def innerRNG(list: List[Int]): RNG = list match {
      case Nil => innerRNG(as.toList)
      case h::t => new RNG { override def nextInt = (h, innerRNG(t)) }
    }
    innerRNG(as.toList)
  }

  // 연습문제 8.5
  it should "have some specific gnerators" in {
    Gen.unit(37).sample.run(rng(7))._1 should be (37)
    Gen.boolean.sample.run(rng(7))._1 should be (true)
    Gen.boolean.sample.run(rng(2))._1 should be (false)
    Gen.listOfN(4, Gen.choose(0, 10)).sample.run(rng(7))._1 should
      be (List(7, 7, 7, 7))
    Gen.listOfN(4, Gen.choose(0, 10)).sample.run(rng(1, 3, 7))._1 should
      be (List(1, 3, 7, 1))
  }
}
