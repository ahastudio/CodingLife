import org.scalatest._

trait Monoid[A] {
  def op(a1: A, a2: A): A
  def zero: A
}

sealed trait WC
case class Stub(chars: String) extends WC
case class Part(lStub: String, words: Int, rStub: String) extends WC

trait Monoids {
  def foldMapV[A, B](v: IndexedSeq[A], m: Monoid[B])(f: A => B): B = v match {
    case IndexedSeq(a) => f(a)
    case _ => {
      val half = v.length / 2
      m.op(foldMapV(v.take(half), m)(f), foldMapV(v.drop(half), m)(f))
    }
  }

  val wcMonoid = new Monoid[WC] {
    def op(a: WC, b: WC): WC = (a, b) match {
      case (Stub(a), Stub(b)) => Stub(a + b)
      case (Part(al, aw, ar), Stub(b)) => Part(al, aw, ar + b)
      case (Stub(a), Part(bl, bw, br)) => Part(a + bl, bw, br)
      case (Part(al, aw, ar), Part(bl, bw, br)) =>
        Part(al, aw + bw + Seq(ar + bl).filter(_.trim.nonEmpty).length, br)
    }
    def zero: WC = Stub("")
  }

  def countWords(chars: String) = {
    if (chars.trim.isEmpty) 0
    else {
      val size = 5
      val stubs = (0 to chars.length / size)
        .map(i => chars.drop(i * size).take(size))
        .filter(_.nonEmpty)
        .map(Stub(_))
      def f(stub: Stub): WC = {
        if (!stub.chars.contains(' ')) stub
        else {
          val words = stub.chars.trim.split("\\s+")
          val Pattern = "(.)(.*)(.)".r
          stub.chars match {
            case Pattern(" ", _, " ") => Part("", words.length, "")
            case Pattern(_, _, " ") => Part(words.head, words.length - 1, "")
            case Pattern(" ", _, _) => Part("", words.length - 1, words.last)
            case Pattern(_, _, _) =>
              Part(words.head, words.length - 2, words.last)
            case _ => Part("", words.length, "")
          }
        }
      }
      foldMapV(stubs, wcMonoid)(f) match {
        case Part(a, words, b) => words + Seq(a, b).filter(_.nonEmpty).length
        case Stub(_) => 1
        case _ => 0
      }
    }
  }
}

class TestChapter10 extends FlatSpec with Matchers with Monoids {
  it should "know WordCound Monoid" in {
    val part1 = Part("ab", 1, "c")
    val part2 = Part("d", 2, "")
    wcMonoid.op(Stub("01"), Stub("02")) should be (Stub("0102"))
    wcMonoid.op(part1, Stub("d")) should be (Part("ab", 1, "cd"))
    wcMonoid.op(Stub("c"), part2) should be (Part("cd", 2, ""))
    wcMonoid.op(part1, part2) should be (Part("ab", 4, ""))
    wcMonoid.op(Part("a", 1, ""), Part("", 1, "b")) should
      be (Part("a", 2, "b"))
    wcMonoid.op(part1, wcMonoid.zero) should be (part1)
    wcMonoid.op(wcMonoid.zero, part1) should be (part1)
  }

  it should "count words" in {
    countWords("All your base are belong to us") should be (7)
    countWords("All") should be (1)
    countWords("") should be (0)
    countWords(" All your ") should be (2)
    countWords(" 123  678 ") should be (2)
    countWords(" 1  45  89") should be (3)
    countWords("01234567890123456789012345678901234567890") should be (1)
    countWords("01234567890123456 890123456789012 4567890") should be (3)
    countWords("배민법인결제는 배달의민족과 제휴하여 " +
      "배달음식을 주문하고 간편하게 비용을 청구할 수 있도록 지원합니다."
    ) should be (11)
  }
}
