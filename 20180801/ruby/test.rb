# frozen_string_literal: true

require 'minitest/autorun'

UNIT_PRICE = 8.00
DISCOUNTS = [0, 0, 0.05, 0.10, 0.20, 0.25].freeze

# monkey patch...
class Array
  def minus(other)
    zip(other).map { |a, b| a - b.to_i }
  end

  def minus_package(count)
    minus([1] * count).sort.reverse
  end
end

def cost(books, cache = {})
  return 0 if books.sum.zero?
  n = books.count(&:positive?)
  cache[books] ||=
    (1..n).map { |i| package_cost(i) + cost(books.minus_package(i), cache) }.min
end

def package_cost(count)
  (count * UNIT_PRICE) * (1 - DISCOUNTS[count])
end

# Test ----------------------------------------------------------
class HarryPotterTest < MiniTest::Unit::TestCase
  def test_sample
    assert_equal 51.20, cost([2, 2, 2, 1, 1])
    assert_equal 102.40, cost([4, 4, 4, 2, 2])
  end

  def test_zero
    assert_equal 0.00, cost([0, 0, 0, 0, 0])
  end

  def test_one
    assert_equal 8.00, cost([1, 0, 0, 0, 0])
    assert_equal 15.20, cost([1, 1, 0, 0, 0])
    assert_equal 21.60, cost([1, 1, 1, 0, 0])
    assert_equal 25.60, cost([1, 1, 1, 1, 0])
    assert_equal 30.00, cost([1, 1, 1, 1, 1])
  end

  def test_two
    assert_equal 16.00, cost([2, 0, 0, 0, 0])
    assert_equal 23.20, cost([2, 1, 0, 0, 0])
  end

  def test_large
    assert_equal 150.00, cost([5, 5, 5, 5, 5])
    assert_equal 132.40, cost([5, 5, 5, 3, 3])
  end

  def test_minus
    assert_equal [0, 0, 0], [1, 1, 1].minus([1, 1, 1])
    assert_equal [0, 1, 2], [1, 2, 3].minus([1, 1, 1])
    assert_equal [0, 1, 3], [1, 2, 3].minus([1, 1])
  end
end
