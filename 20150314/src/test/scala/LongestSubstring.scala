import org.scalatest._

object String {
    def longest_substring(string: String) = {
        substrings(string).filter(hasNotRepeat).maxBy(_.length)
    }

    def substrings(string: String) = {
        (0 until string.length).map { start =>
            (start + 1 to string.length).map(string.substring(start, _))
        }.flatten
    }

    def hasNotRepeat(string: String) = {
        string.length == string.toSet.toList.length
    }
}

class TestLongestSubstring extends FlatSpec with Matchers {
    it should "find the longest substring without repeating characters" in {
        String.longest_substring("abcabcbb") should be ("abc")
        String.longest_substring("aabcabcbb") should be ("abc")
        String.longest_substring("bbbb") should be ("b")
    }

    it should "get substrings" in {
        String.substrings("abc").sorted should be (List("a", "ab", "abc", "b", "bc", "c"))
    }

    it should "check the string has repeating characters" in {
        String.hasNotRepeat("abc") should be (true)
        String.hasNotRepeat("a") should be (true)
        String.hasNotRepeat("abcd") should be (true)
        String.hasNotRepeat("abca") should be (false)
    }
}
