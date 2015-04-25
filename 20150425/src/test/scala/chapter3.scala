import org.scalatest._

sealed trait Tree[+A]
case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {
  def size[A](tree: Tree[A]): Int = tree match {
    case Leaf(_) => 1
    case Branch(left, right) => size(left) + size(right) + 1
  }

  def maximum(tree: Tree[Int]): Int = tree match {
    case Leaf(a) => a
    case Branch(left, right) => maximum(left) max maximum(right)
  }

  def depth[A](tree: Tree[A]): Int = tree match {
    case Leaf(_) => 1
    case Branch(left, right) => depth(left) max depth(right) + 1
  }

  def map[A, B](tree: Tree[A])(f: A => B): Tree[B] = tree match {
    case Leaf(a) => Leaf(f(a))
    case Branch(left, right) => Branch(map(left)(f), map(right)(f))
  }

  def fold[A, B](tree: Tree[A])(lFn: A => B)(bFn: (B, B) => B): B = tree match {
    case Leaf(a) => lFn(a)
    case Branch(left, right) => bFn(fold(left)(lFn)(bFn), fold(right)(lFn)(bFn))
  }

  def size2[A](tree: Tree[A]): Int = fold(tree)(a => 1)(_ + _ + 1)

  def maximum2(tree: Tree[Int]): Int = fold(tree)(a => a)(_ max _)

  def depth2[A](tree: Tree[A]): Int = fold(tree)(a => 1)(_ max _ + 1)

  def map2[A, B](tree: Tree[A])(f: A => B): Tree[B] =
    fold(tree) { a => Leaf(f(a)): Tree[B] } { (a, b) => Branch(a, b) }
}

class TestChapter3 extends FlatSpec with Matchers {
  // 연습문제 3.25
  it should "count node in the tree" in {
    Tree.size(Leaf(1)) should be (1)
    Tree.size(Branch(Leaf(1), Leaf(2))) should be (3)
    Tree.size(Branch(Leaf(1), Branch(Leaf(3), Leaf(4)))) should be (5)
  }

  // 연습문제 3.26
  it should "find maximum value in the tree" in {
    Tree.maximum(Leaf(10)) should be (10)
    Tree.maximum(Branch(Leaf(10), Leaf(2))) should be (10)
    Tree.maximum(Branch(Leaf(1), Branch(Leaf(3), Leaf(4)))) should be (4)
  }

  // 연습문제 3.27
  it should "get the longest depth of the tree" in {
    Tree.depth(Leaf(1)) should be (1)
    Tree.depth(Branch(Leaf(1), Leaf(2))) should be (2)
    Tree.depth(Branch(Leaf(1), Branch(Leaf(3), Leaf(4)))) should be (3)
  }

  // 연습문제 3.28
  it should "do something each leaf" in {
    Tree.map(Leaf(1))(_ + 1) should be (Leaf(2))
    Tree.map(Branch(Leaf(1), Leaf(2)))(_ + 1) should
      be (Branch(Leaf(2), Leaf(3)))
    Tree.map(Branch(Leaf(1), Branch(Leaf(3), Leaf(4))))(_ + 1) should
      be (Branch(Leaf(2), Branch(Leaf(4), Leaf(5))))
  }

  // 연습문제 3.29
  it should "rewrite functions using fold" in {
    Tree.size2(Branch(Leaf(1), Branch(Leaf(3), Leaf(4)))) should be (5)
    Tree.maximum2(Branch(Leaf(1), Branch(Leaf(3), Leaf(4)))) should be (4)
    Tree.depth2(Branch(Leaf(1), Branch(Leaf(3), Leaf(4)))) should be (3)
    Tree.map2(Branch(Leaf(1), Branch(Leaf(3), Leaf(4))))(_ + 1) should
      be (Branch(Leaf(2), Branch(Leaf(4), Leaf(5))))
  }
}
