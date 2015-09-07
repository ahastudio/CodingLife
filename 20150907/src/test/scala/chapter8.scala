import org.scalatest._

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

case class Gen[+A](sample: State[RNG, A]) {
  def flatMap[B](f: A => Gen[B]): Gen[B] = {
    def random(rng: RNG): (B, RNG) = {
      val (value, nextRNG) = sample.run(rng)
      f(value).sample.run(nextRNG)
    }
    Gen(State(random))
  }

  def listOfN(size: Gen[Int]): Gen[List[A]] = {
    def randomList(rng: RNG): (List[A], RNG) = {
      val (n, nextRNG) = size.sample.run(rng)
      (1 to n).foldLeft((List[A](), nextRNG)) { (a, e) =>
        val (list, rng) = a
        val (value, nextRNG) = sample.run(rng)
        (list :+ value, nextRNG)
      }
    }
    Gen(State(randomList))
  }

  def unsized: SGen[A] = SGen(a => this)
}

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
    Gen(State((rng: RNG) => (a, rng)))
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

  def union[A](g1: Gen[A], g2: Gen[A]): Gen[A] = {
    def random(rng: RNG): (A, RNG) = {
      val (bool, nextRNG) = boolean.sample.run(rng)
      val g = if (bool) g1 else g2
      g.sample.run(nextRNG)
    }
    Gen(State(random))
  }

  def weighted[A](g1: (Gen[A], Double), g2: (Gen[A], Double)): Gen[A] = {
    def randomDouble(rng: RNG): (Double, RNG) = {
      val (value, nextRNG) = rng.nextInt
      (value.toDouble / Int.MaxValue, nextRNG)
    }
    def random(rng: RNG): (A, RNG) = {
      val (value, nextRNG) = randomDouble(rng)
      val g = if (value < g1._2 / (g1._2 + g2._2)) g1._1 else g2._1
      g.sample.run(nextRNG)
    }
    Gen(State(random))
  }
}

//

case class SGen[+A](forSize: Int => Gen[A])

//

object MyStream {
  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = {
    f(z) match {
      case None => Stream.empty
      case Some((a, state)) => a #:: unfold(state)(f)
    }
  }
}

//

object Prop {
  type SuccessCount = Int
  type FailedCase = String

  type TestCases = Int

  def forAll[A](as: Gen[A])(f: A => Boolean): Prop = Prop {
    (n, rng) =>  randomStream(as)(rng).zip(Stream.from(0)).take(n).map {
      case (a, i) =>
        try {
          if (f(a)) Passed
          else Falsified(a.toString, i)
        } catch {
          case e: Exception => Falsified(buildMsg(a, e), i)
        }
    }.find(_.isFalsified).getOrElse(Passed)
  }

  def randomStream[A](g: Gen[A])(rng: RNG): Stream[A] =
    MyStream.unfold(rng)(rng => Some(g.sample.run(rng)))

  def buildMsg[A](s: A, e: Exception): String =
    s"test case: $s\n" +
    s"generated an exception: ${e.getMessage}\n" +
    s"stack trace:\n ${e.getStackTrace.mkString("\n")}"
}

sealed trait Result {
  def isFalsified: Boolean
}

case object Passed extends Result {
  def isFalsified = false
}

case class Falsified(failure: Prop.FailedCase,
                     successes: Prop.SuccessCount) extends Result {
  def isFalsified = true
}

case class Prop(run: (Prop.TestCases, RNG) => Result) {
  def &&(p: Prop): Prop = {
    Prop((testCases, rng) => {
      def check(ps: Seq[(Prop, String)]): Result = ps match {
        case Nil => Passed
        case (prop, tag) :: tail => {
          prop.run(testCases, rng) match {
            case Falsified(failure, successes) =>
              Falsified(tag + " - " + failure, successes)
            case Passed =>
              check(tail)
          }
        }
      }
      check(Seq((this, "lhs"), (p, "rhs")))
      // new solve ----
      Seq((this, "lhs"), (p, "rhs")).foldLeft(Passed: Result) { (a, e) =>
        if (a.isFalsified) a
        else {
          e._1.run(testCases, rng) match {
            case Falsified(failure, successes) =>
              Falsified(e._2 + " - " + failure, successes)
            case Passed => Passed
          }
        }
      }
    })
  }

  def ||(p: Prop): Prop = {
    Prop((testCases, rng) => {
      Seq((this, "lhs"), (p, "rhs")).foldLeft(Falsified("", 0): Result) {
        (a, e) => {
          if (!a.isFalsified) a
          else {
            e._1.run(testCases, rng) match {
              case Falsified(failure, successes) =>
                Falsified(e._2 + " - " + failure, successes)
              case Passed => Passed
            }
          }
        }
      }
    })
  }
}

//

class TestChapter8 extends FlatSpec with Matchers {
  def rng(as: Int*): RNG = {
    def innerRNG(list: List[Int]): RNG = list match {
      case Nil => innerRNG(as.toList)
      case h::t => new RNG { override def nextInt = (h, innerRNG(t)) }
    }
    innerRNG(as.toList)
  }

  // 연습문제 8.9
  it should "'&&' and '||' functions" in {
    def isEven(x: Int): Boolean = (x % 2) == 0

    (Prop.forAll(Gen.unit(2))(isEven) && Prop.forAll(Gen.unit(4))(isEven))
      .run(1, rng(1)) should be (Passed)
    (Prop.forAll(Gen.unit(1))(isEven) && Prop.forAll(Gen.unit(4))(isEven))
      .run(1, rng(1)) should be (Falsified("lhs - 1", 0))
    (Prop.forAll(Gen.unit(2))(isEven) && Prop.forAll(Gen.unit(3))(isEven))
      .run(1, rng(1)) should be (Falsified("rhs - 3", 0))
    (Prop.forAll(Gen.unit(1))(isEven) && Prop.forAll(Gen.unit(3))(isEven))
      .run(1, rng(1)) should be (Falsified("lhs - 1", 0))

    (Prop.forAll(Gen.unit(2))(isEven) || Prop.forAll(Gen.unit(4))(isEven))
      .run(1, rng(1)) should be (Passed)
    (Prop.forAll(Gen.unit(1))(isEven) || Prop.forAll(Gen.unit(4))(isEven))
      .run(1, rng(1)) should be (Passed)
    (Prop.forAll(Gen.unit(2))(isEven) || Prop.forAll(Gen.unit(3))(isEven))
      .run(1, rng(1)) should be (Passed)
    (Prop.forAll(Gen.unit(1))(isEven) || Prop.forAll(Gen.unit(3))(isEven))
      .run(1, rng(1)) should be (Falsified("rhs - 3", 0))
  }

  // 연습문제 8.10
  it should "convert Gen to SGen" in {
    Gen.unit(10).unsized.forSize(15151).sample.run(rng(1))._1 should be (10)
  }
}
