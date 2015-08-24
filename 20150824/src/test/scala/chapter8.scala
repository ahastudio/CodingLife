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

case class Gen[A](sample: State[RNG, A]) {
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

case class Prop(run: (Prop.TestCases, RNG) => Result)

//

class TestChapter8 extends FlatSpec with Matchers {
  def rng(as: Int*): RNG = {
    def innerRNG(list: List[Int]): RNG = list match {
      case Nil => innerRNG(as.toList)
      case h::t => new RNG { override def nextInt = (h, innerRNG(t)) }
    }
    innerRNG(as.toList)
  }

  // 연습문제 8.6
  it should "have some specific methods" in {
    def booleanToGenStr(a: Boolean): Gen[String] =
      if (a) Gen.unit("T") else Gen.unit("F")

    Gen.boolean.flatMap(booleanToGenStr).sample.run(rng(1))._1 should be ("T")
    Gen.boolean.flatMap(booleanToGenStr).sample.run(rng(2))._1 should be ("F")

    Gen.boolean.listOfN(Gen.unit(3)).sample.run(rng(1, 2))._1 should
      be (List(true, false, true))

    val gen = Gen.boolean.flatMap(booleanToGenStr).listOfN(Gen.unit(3))
    gen.sample.run(rng(1, 2))._1 should be (List("T", "F", "T"))
  }

  // 연습문제 8.7
  it should "union two functions" in {
    def g1 = Gen.unit("L")
    def g2 = Gen.unit("R")
    Gen.union(g1, g2).sample.run(rng(1))._1 should be ("L")
    Gen.union(g1, g2).sample.run(rng(2))._1 should be ("R")
  }

  // 연습문제 8.8
  it should "union two functions with weight" in {
    def g1 = (Gen.unit("L"), 1.0)
    def g2 = (Gen.unit("R"), 0.5)
    def boundary = Int.MaxValue * 1.0 / (1.0 + 0.5)
    Gen.weighted(g1, g2).sample.run(rng(boundary.toInt - 1))._1 should be ("L")
    Gen.weighted(g1, g2).sample.run(rng(boundary.toInt + 1))._1 should be ("R")
  }
}
