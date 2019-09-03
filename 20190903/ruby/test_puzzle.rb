# frozen_string_literal: true

require 'minitest/autorun'

def find_num_of_valid_words(words, puzzles)
  puzzles.map do |puzzle|
    words.count { |word| valid?(word, puzzle) }
  end
end

def valid?(word, puzzle)
  word.include?(puzzle[0]) && word.chars.all? { |i| puzzle.include?(i) }
end

# Test
class RomanNumeralsTest < MiniTest::Unit::TestCase
  def test_sample
    words = %w[aaaa asas able ability actt actor access]
    puzzles = %w[aboveyz abrodyz abslute absoryz actresz gaswxyz]
    assert_equal [1, 1, 3, 2, 4, 0], find_num_of_valid_words(words, puzzles)
  end

  def test_sample2
    words = %w[apple pleas please]
    puzzles = %w[aelwxyz aelpxyz aelpsxy saelpxy xaelpsy]
    assert_equal [0, 1, 3, 2, 0], find_num_of_valid_words(words, puzzles)
  end

  def test_valid?
    assert valid?('a', 'aboveyz')
    assert valid?('ab', 'aboveyz')
    assert !valid?('abc', 'aboveyz')
  end
end
