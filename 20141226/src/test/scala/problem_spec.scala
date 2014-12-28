import scala.annotation.tailrec
import org.scalatest._

object Solve {
  def isOdd(n: BigInt) = (n % 2 == 1)

  def nextNumber(n: BigInt): BigInt = {
    if (isOdd(n)) (3 * n + 1)
    else (n / 2)
  }

  def getLength(n: Int): Int = {
    @tailrec def getLengthRec(length: Int, n: BigInt): Int = {
      if (n == 1) length + 1
      else getLengthRec(length + 1, nextNumber(n))
    }
    getLengthRec(0, n)
  }

  def getMaxLength(begin: Int, end: Int) = {
    (begin to end).map(getLength).max
  }
}

class ProblemSolveSpec extends FlatSpec with Matchers {
  it should "get length" in {
    Solve.getLength(1) should be (1)
    Solve.getLength(2) should be (2)
    Solve.getLength(3) should be (8)
    Solve.getLength(10) should be (7)
  }

  it should "get max length" in {
    Solve.getMaxLength(1, 10) should be (20)
    Solve.getMaxLength(100, 200) should be (125)
    Solve.getMaxLength(201, 210) should be (89)
    Solve.getMaxLength(900, 1000) should be (174)
    Solve.getMaxLength(1, 100000) should be (351)
    Solve.getMaxLength(1, 200000) should be (383)
    Solve.getMaxLength(1, 1000000) should be (525)
  }
}
