import org.scalatest._

object Solve {
  def isOdd(n: Int) = (n % 2 == 1)

  def getLength(n: Int): Int = {
    if (n == 1) 1
    else if (isOdd(n)) getLength(3 * n + 1) + 1
    else getLength(n / 2) + 1
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
  }
}
