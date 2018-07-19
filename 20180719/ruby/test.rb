# frozen_string_literal: true

require 'minitest/autorun'

# Monkey patch
class Array
  def jolly?
    differences.sort == [*1...size]
  end

  def differences
    slice(0, size - 1).zip(slice(1, size - 1)).map { |a, b| (a - b).abs }
  end
end

# Test ----------------------------------------------------------
class JollyJumpersTest < MiniTest::Unit::TestCase
  def test_jolly
    assert_equal true, [1, 4, 2, 3].jolly?
    assert_equal false, [1, 4, 2, -1, 6].jolly?
    assert_equal true, [11, 7, 4, 2, 1, 6].jolly?
  end

  def test_differences
    assert_equal [3, 2, 1], [1, 4, 2, 3].differences
    assert_equal [3, 2, 3, 7], [1, 4, 2, -1, 6].differences
  end
end
