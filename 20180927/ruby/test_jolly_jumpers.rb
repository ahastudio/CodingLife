# frozen_string_literal: true

require 'minitest/autorun'

# Monkey patch
class Array
  def jolly_jumper?
    differences.to_set == Set.new(1...size)
  end

  def differences
    self[0...-1].zip(self[1..-1]).map { |a, b| (a - b).abs }
  end
end

# Jolly Jumpers Test
class JollyJumpersTest < MiniTest::Unit::TestCase
  def test_jolly_jumpers
    [
      [1, 2, 4],
      [2, 1, 3],
      [3, 1, 2],
      [2, 1, 3, 6],
      [11, 7, 4, 2, 1, 6]
    ].each { |i| assert i.jolly_jumper?, "Array = #{i}" }
  end

  def test_not_jolly_jumpers
    [
      [1, 2, 3],
      [2, 1, 3, 4],
      [1, 4, 2, -1, 6]
    ].each { |i| assert !i.jolly_jumper?, "Array = #{i}" }
  end

  def test_differences
    assert_equal [1, 2], [1, 2, 4].differences
    assert_equal [1, 1], [1, 2, 3].differences
    assert_equal [1, 2], [2, 1, 3].differences
    assert_equal [1, 2, 3], [2, 1, 3, 6].differences
  end
end
