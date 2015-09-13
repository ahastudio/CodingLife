import org.scalatest._

class Test extends FlatSpec with Matchers {
  // 0.3 배열 회전

  // 코드 0-8

  def right_rotate(as: Array[Int], s: Int, t: Int): Array[Int] =
    as.take(s) ++ Array(as(t)) ++ as.slice(s, t) ++ as.drop(t + 1)

  def left_rotate(as: Array[Int], s: Int, t: Int): Array[Int] =
    as.take(s) ++ as.slice(s + 1, t + 1) ++ Array(as(s)) ++ as.drop(t + 1)

  def right_rotate_n(as: Array[Int], s: Int, t: Int, k: Int): Array[Int] = {
    as.take(s) ++ as.slice(t - k + 1, t + 1) ++ as.slice(s, k + 1) ++
      as.drop(t + 1)
  }

  it should "rotate an array" in {
    right_rotate(Array(1, 2, 3, 4, 5, 6, 7, 8), 2, 6) should
      be(Array(1, 2, 7, 3, 4, 5, 6, 8))

    left_rotate(Array(1, 2, 3, 4, 5, 6, 7, 8), 2, 6) should
      be(Array(1, 2, 4, 5, 6, 7, 3, 8))

    right_rotate_n(Array(1, 2, 3, 4, 5, 6, 7, 8), 2, 6, 3) should
      be(Array(1, 2, 5, 6, 7, 3, 4, 8))
  }

  // 0.4 은행 대기번호 관리

  // Queue

  def enqueue(as: List[Int], n: Int): List[Int] = as ++ List(n)

  def dequeue(as: List[Int]): (List[Int], Int) = as match {
    case Nil => (Nil, 0)
    case h :: t => (t, h)
  }

  it should "enqueue and dequeue" in {
    var queue = List[Int]()
    for (i <- Seq(1, 2, 3, 4, 5))
      queue = enqueue(queue, i)
    for (i <- Seq(1, 2, 3)) {
      val (q, n) = dequeue(queue)
      n should be (i)
      queue = q
    }
    for (i <- Seq(6, 7, 8, 9, 10))
      queue = enqueue(queue, i)
    for (i <- Seq(4, 5, 6, 7, 8, 9, 10)) {
      val (q, n) = dequeue(queue)
      n should be (i)
      queue = q
    }
    dequeue(queue) should be ((Nil, 0))
  }

  // Stack

  def push(as: List[Int], n: Int): List[Int] = n :: as

  def pop(as: List[Int]): (List[Int], Int) = as match {
    case Nil => (Nil, 0)
    case h :: t => (t, h)
  }

  it should "push and pop" in {
    var stack = List[Int]()
    for (i <- Seq(1, 2, 3, 4, 5))
      stack = push(stack, i)
    for (i <- Seq(5, 4, 3)) {
      val (s, n) = pop(stack)
      n should be (i)
      stack = s
    }
    for (i <- Seq(6, 7, 8, 9, 10))
      stack = push(stack, i)
    for (i <- Seq(10, 9, 8, 7, 6, 2, 1)) {
      val (s, n) = pop(stack)
      n should be (i)
      stack = s
    }
    pop(stack) should be ((Nil, 0))
  }
}
