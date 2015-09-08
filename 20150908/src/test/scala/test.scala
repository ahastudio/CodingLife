import org.scalatest._

class Test extends FlatSpec with Matchers {
  // 0.1 최대와 최소

  // 코드 0-1

  def min(x: Int, y: Int): Int =
    if (x < y) x
    else y

  def max(x: Int, y: Int): Int =
    if (x > y) x
    else y

  it should "get min and max value" in {
    min(1, 2) should be (1)
    max(1, 2) should be (2)
  }

  // 코드 0-2

  def max_arr(as: List[Int]): Int = {
    var maxA = as(0)
    for (i <- as.tail)
      if (i > maxA)
        maxA = i
    maxA
  }

  it should "get max value in array" in {
    max_arr(List(1, 2, 3, 4, 5)) should be (5)
  }

  // 코드 0-3

  def max2(x: => Int, y: => Int): Int = if (x > y) x else y
  def min2(x: => Int, y: => Int): Int = if (x < y) x else y

  it should "get min and max value (#2)" in {
    (2 * max2(1, 2)) should be (4)
    (2 * min2(1, 2)) should be (2)

    var x = 2
    max({ x *= 2; x }, 1) should be (4) // x * 2 = 4
    var y = 2
    max2({ y *= 2; y }, 1) should be (8) // x * 2 = 4 ...but... WTF!

    var i = 4
    max2(3, { i += 1; i }) should be (6) // WTF!
  }

  // 코드 0-4

  def max_arr2(as: List[Int]): Int =
    if (as.length == 1) as.head
    else max2(as.last, max_arr2(as.init))
    // max_arr2를 if와 return에서 중복으로 호출하게 됨 = 지옥. 파멸. 성공적.

  it should "get max value in array (#2)" in {
    max_arr2(List(1, 2, 3, 4, 5)) should be (5)
  }
}
