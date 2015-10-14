class String
  def letters
    split('')
  end

  def substring_without_repeating
    letters.reduce('') { |s, c| return s if s.include?(c); s << c }
  end

  def longest_substring
    if size > 1
      [substring_without_repeating, self[1..-1].longest_substring].
        max_by{|i| i.size}
    else
      self
    end
  end
end

puts "-----------"

describe "String#longest_subsring" do
  it "finds the longest substring without repeating characters" do
    expect("abcabcbb".longest_substring).to eq("abc")
    expect("bbbbb".longest_substring).to eq("b")
    expect("bbba".longest_substring).to eq("ba")
    expect("abcabcbbabcd".longest_substring).to eq("abcd")
  end
end
