# frozen_string_literal: true

require 'minitest/autorun'

# Monkey patch
class Integer
  ITEMS = {
    1000 => 'M', 900 => 'CM',
    500 => 'D', 400 => 'CD',
    100 => 'C', 90 => 'XC',
    50 => 'L', 40 => 'XL',
    10 => 'X', 9 => 'IX',
    5 => 'V', 4 => 'IV',
    1 => 'I'
  }.freeze

  def _roman
    n = self
    result = ''
    while n.positive?
      x, letter = ITEMS.find { |k, _| n >= k }
      result += letter
      n -= x
    end
    result
  end

  def roman
    return '' if zero?

    x, letter = ITEMS.find { |k, _| self >= k }
    letter + (self - x).roman
  end
end

# Roman Numerals Test
class RomanNumeralsTest < MiniTest::Unit::TestCase
  def test_sample
    assert_equal 'MLXVI', 1066.roman
    assert_equal 'MDCCLXXVI', 1776.roman
    assert_equal 'MCMLIV', 1954.roman
  end

  def test_simple
    assert_equal 'I', 1.roman
    assert_equal 'II', 2.roman
    assert_equal 'III', 3.roman
    assert_equal 'IV', 4.roman
    assert_equal 'V', 5.roman
  end

  def test_near_ten
    assert_equal 'VI', 6.roman
    assert_equal 'X', 10.roman
    assert_equal 'IX', 9.roman
  end
end
