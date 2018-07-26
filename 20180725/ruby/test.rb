# frozen_string_literal: true

require 'minitest/autorun'

SYMBOLS = {
  1 => 'I', 5 => 'V',
  4 => 'IV', 9 => 'IX',
  10 => 'X', 50 => 'L',
  40 => 'XL', 90 => 'XC',
  100 => 'C', 500 => 'D',
  400 => 'CD', 900 => 'CM',
  1000 => 'M'
}.freeze

NUMBERS = SYMBOLS.keys.sort.reverse.freeze

# monkey patch...
class Integer
  def roman
    return '' if zero?
    n = NUMBERS.find { |i| self >= i }
    SYMBOLS[n] + (self - n).roman
  end
end

# Test ----------------------------------------------------------
class RomanNumeralsTest < MiniTest::Unit::TestCase
  def test_simple
    assert_equal 'I', 1.roman
    assert_equal 'II', 2.roman
    assert_equal 'III', 3.roman
  end

  def test_five
    assert_equal 'V', 5.roman
    assert_equal 'VI', 6.roman
  end

  def test_ten
    assert_equal 'X', 10.roman
    assert_equal 'XI', 11.roman
  end

  def test_four_and_nine
    assert_equal 'IV', 4.roman
    assert_equal 'IX', 9.roman
    assert_equal 'XLIX', 49.roman
  end

  def test_sample
    assert_equal 'XXXIX', 39.roman
    assert_equal 'CCXLVI', 246.roman
    assert_equal 'CCVII', 207.roman
    assert_equal 'MLXVI', 1066.roman
    assert_equal 'MDCCLXXVI', 1776.roman
    assert_equal 'MCMLIV', 1954.roman
  end
end
