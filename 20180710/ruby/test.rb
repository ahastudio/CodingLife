# frozen_string_literal: true

require 'minitest/autorun'

# Monkey patch
class Integer
  def digits
    to_s.split('').map(&:to_i)
  end

  def d
    digits.sum + self
  end
end

def self_numbers(max)
  [*1..max] - (1..max).map(&:d)
end

# Test ----------------------------------------------------------
class SelfNumberTest < MiniTest::Unit::TestCase
  def test_function_d
    assert_equal 9 + 1 + 91, 91.d
    assert_equal 1 + 0 + 100, 100.d
  end

  def test_self_numbers
    assert_equal [1, 3, 5, 7, 9, 20, 31], self_numbers(31)
  end

  def test_solve
    assert_equal 1_227_365, self_numbers(5_000 + 1).sum
    assert_equal 122_295_392, self_numbers(50_000 + 1).sum
  end
end
