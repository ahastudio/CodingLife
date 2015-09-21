import org.scalatest._

trait ParseError
case object NoMatching extends ParseError

case class Parser[A](check: String => Either[ParseError, A]) {
  def or(other: Parser[A]): Parser[A] = {
    Parser.or(this, other)
  }
}

object Parser {
  def or[A](p1: Parser[A], p2: Parser[A]): Parser[A] = {
    Parser { input =>
      p1.check(input) match {
        case Right(s) => Right(s)
        case Left(_) => p2.check(input)
      }
    }
  }
}

object Parsers {
  type Result[A] = Either[ParseError, A]

  def run[A](p: Parser[A])(input: String): Result[A] = {
    p.check(input)
  }

  def char(c: Char): Parser[Char] = {
    Parser { input =>
      if (!input.isEmpty && input.charAt(0) == c) Right(c)
      else Left(NoMatching)
    }
  }

  def string(s: String): Parser[String] = {
    Parser { input =>
      if (input.startsWith(s)) Right(s)
      else Left(NoMatching)
    }
  }

  def listOfN[A](n: Int, p: Parser[A]): Parser[List[A]] = {
    Parser { input =>
      def check(p: Parser[A], s: String): (Result[A], String) = {
        (1 to s.length).foldLeft[(Result[A], String)]((Left(NoMatching), s)) {
          (a, e) => a match {
            case (Right(a), remain) => (Right(a), remain)
            case (Left(_), s) => p.check(s.take(e)) match {
              case Right(a) => (Right(a), s.drop(e))
              case Left(a) => (Left(a), s)
            }
          }
        }
      }
      val (list, remain) = (1 to n).foldLeft((List[A](), input)) { (acc, e) =>
        check(p, acc._2) match {
          case (Right(a), remain) => (acc._1 ++ List(a), remain)
          case (Left(_), _) => acc
        }
      }
      if (list.length == n) Right(list)
      else Left(NoMatching)
    }
  }
}

class TestChapter9 extends FlatSpec with Matchers {
  it should "parses a character" in {
    Parsers.run(Parsers.char('a'))("a") should be (Right('a'))
    Parsers.run(Parsers.char('a'))("ab") should be (Right('a'))
    Parsers.run(Parsers.char('a'))("b") should be (Left(NoMatching))
    Parsers.run(Parsers.char('a'))("ba") should be (Left(NoMatching))
    Parsers.run(Parsers.char('a'))("") should be (Left(NoMatching))
  }

  it should "parses a string" in {
    Parsers.run(Parsers.string("abc"))("abc") should be (Right("abc"))
    Parsers.run(Parsers.string("abc"))("abcd") should be (Right("abc"))
    Parsers.run(Parsers.string("abc"))("bc") should be (Left(NoMatching))
    Parsers.run(Parsers.string("abc"))("cabc") should be (Left(NoMatching))
  }

  it should "combines two parsers" in {
    val parser = Parsers.string("abra") or Parsers.string("cadabra")
    Parsers.run(parser)("abra") should be (Right("abra"))
    Parsers.run(parser)("cadabra") should be (Right("cadabra"))
    Parsers.run(parser)("a") should be (Left(NoMatching))
  }

  it should "checks list of N" in {
    val parser = Parsers.string("ab") or Parsers.string("cad")
    Parsers.run(Parsers.listOfN(3, parser))("ababcad") should
      be (Right(List("ab", "ab", "cad")))
    Parsers.run(Parsers.listOfN(3, parser))("cadabab") should
      be (Right(List("cad", "ab", "ab")))
    Parsers.run(Parsers.listOfN(3, parser))("ababab") should
      be (Right(List("ab", "ab", "ab")))
    Parsers.run(Parsers.listOfN(3, parser))("abab") should
      be (Left(NoMatching))
  }
}
