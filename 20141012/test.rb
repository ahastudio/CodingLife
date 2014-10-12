class String
  def letters
    split('')
  end

  def match?(pattern)
    reverse.pattern_match(pattern.reverse)
  end

  def pattern_match(pattern)
    if empty? and pattern.empty?
      true
    elsif pattern[0] == '*'
      index = letters.index { |i| pattern[1] != '.' and i != pattern[1] }
      (index.nil? ? "" : self[index..-1]).pattern_match(pattern[2..-1])
    else
      (self[0] == pattern[0]) and self[1..-1].pattern_match(pattern[1..-1])
    end
  end
end

puts "--------------------------------------------"

describe "Regular Expression Matching" do
  it "checks two strings" do
    expect("aa").not_to be_match("a")
    expect("aa").to be_match("aa")
    expect("aa").not_to be_match("aaa")
  end

  it "checks with wildcard" do
    expect("aa").to be_match("a*")
    expect("ab").not_to be_match("a*")
    expect("baa").to be_match("ba*")
    expect("baab").to be_match("ba*b")
    expect("aa").to be_match(".*")
    expect("ab").to be_match(".*")
    expect("aab").to be_match("c*a*b")
    expect("aab").to be_match(".*a*b")
    expect("aacab").to be_match(".*a*b")
  end
end
