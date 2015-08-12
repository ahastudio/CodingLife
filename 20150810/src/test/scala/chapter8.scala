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
}

class TestChapter8 extends FlatSpec with Matchers {
  // 연습문제 8.3
  it should "concat perperties" in {
    (RightProp(1) && RightProp(1)) should be(RightProp(2))
    (RightProp(1) && LeftProp("rhs")) should be(LeftProp("rhs", 1))
    (LeftProp("lhs") && RightProp(1)) should be(LeftProp("lhs", 1))
    (LeftProp("lhs") && LeftProp("rhs")) should be(LeftProp("lhs", 0))
  }

  // 연습문제 8.4
  it should "generate choose" in {
    val rng = new RNG {
      override def nextInt = (7, this)
    }
    Gen.choose(0, 100).sample.run(rng)._1  should be (7)
    Gen.choose(1, 4).sample.run(rng)._1  should be (2)
  }
}
