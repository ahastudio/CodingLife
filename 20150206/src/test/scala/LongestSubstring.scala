import org.scalatest._

object Substring {
    def longest(string: String) = {
        substrings(string).filter(hasNotRepeat).maxBy(_.length)
    }
    
    def hasNotRepeat(string: String) = {
        string.length == string.toSet.toList.length
    }

    def substrings(string: String) = {
        (0 until string.length).map{ start =>
            (start + 1 to string.length).map(string.substring(start, _))
        }.flatten
    }
}

class TestStringLength extends FlatSpec with Matchers {
	it should "testLength" in {
        Substring.longest("abcabcbb") should be ("abc")
        Substring.longest("aabcabcbb") should be ("abc")
        Substring.longest("aaa") should be ("a")
	}

    it should "know the string has repeat letter" in {
        Substring.hasNotRepeat("abc") should be (true)
        Substring.hasNotRepeat("a") should be (true)
        Substring.hasNotRepeat("abca") should be (false)
    }

    it should "get substrings" in {
        Substring.substrings("abc").sorted should be (List("a", "ab", "abc", "bc", "b", "c").sorted)
    }
}
