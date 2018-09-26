# frozen_string_literal: true

require 'minitest/autorun'

# Monkey patch...
class Integer
  def self_number?
    !(1...self).map(&:generate).include?(self)
  end

  def generate
    self + digits.sum
  end

  def digits
    to_s.split('').map(&:to_i)
  end

  def self_numbers
    (1..self).to_a - (1...self).map(&:generate)
  end
end

# Self Number Test
class SelfNumberTest < MiniTest::Unit::TestCase
  def test_example
    # assert_equal 1_227_365, (1..5000).select(&:self_number?).sum
    assert_equal 1_227_365, 5000.self_numbers.sum
  end

  def test_self_numbers
    assert_equal [1, 3], 4.self_numbers
    assert_equal [1, 3, 5], 5.self_numbers
  end

  def test_self_number
    [1, 3, 5, 7, 9, 20, 31].each do |i|
      assert i.self_number?, "number - #{i}"
    end
    [2, 4, 6, 8, 10, 11, 12].each do |i|
      assert !i.self_number?
    end
  end

  def test_generate
    assert_equal 1 + 1, 1.generate
    assert_equal 2 + 2, 2.generate
    assert_equal 10 + 1 + 0, 10.generate
    assert_equal 123 + 1 + 2 + 3, 123.generate
  end

  def test_digits
    assert_equal [1], 1.digits
    assert_equal [2], 2.digits
    assert_equal [1, 0], 10.digits
    assert_equal [1, 2], 12.digits
    assert_equal [1, 2, 3], 123.digits
  end
end
