import org.scalatest._

trait DnaBase {
  def countSubsequences(seq: String, c: Char): Int = c match {
    case 'a' | 'b' | 'c' =>
      if (seq.head != c) countSubsequences(seq.drop(1), c)
      else
        (1 to countContinuous(seq, c)).map { count =>
          countSubsequences(seq.drop(count), next(c))
        }.sum
    case 'd' =>
      1
  }

  def next(c: Char): Char = c match {
    case 'a' => 'b'
    case 'b' => 'c'
    case 'c' => 'd'
    case 'd' => 'a'
  }

  def countContinuous(seq: String, c: Char): Int = {
    def iter(count: Int, seq: String): Int = {
      if (seq.head == c) iter(count + 1, seq.drop(1))
      else count
    }
    iter(0, seq)
  }
}

case class Dna(hexans: String) extends DnaBase {
  def countSubsequences(): Int = {
    countSubsequences(hexans, 'a')
  }
}

class TestAlbocedeDNA extends FlatSpec with Matchers with DnaBase {
  it should "count subsequences" in {
    Dna("abcd").countSubsequences should be (1)
    Dna("aaaabcd").countSubsequences should be (4)
    // Dna("aaaabbccd").countSubsequences should be (28)
  }

  it should "count continuous" in {
    countContinuous("aaaabb", 'a') should be (4)
    countContinuous("aaaabbaa", 'a') should be (4)
  }
}
