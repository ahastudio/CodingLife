# frozen_string_literal: true

require 'minitest/autorun'

def fold(count)
  return [] if count.zero?

  wing = fold(count - 1)
  wing + [0] + wing.reverse.map { |i| 1 - i }
end

# Test
class PaperFoldingTest < MiniTest::Unit::TestCase
  def test_sample
    assert_equal [], fold(0)
    assert_equal [0], fold(1)
    assert_equal [0, 0, 1], fold(2)
    assert_equal [0, 0, 1, 0, 0, 1, 1], fold(3)
    assert_equal [0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1], fold(4)
  end
end
