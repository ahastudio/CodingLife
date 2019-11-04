# frozen_string_literal: true

require 'minitest/autorun'

def squares(width, height)
  h, w = [width, height].sort
  dx = w.to_f / h
  (w * h) - (0...h).map { |y| (dx * (y + 1)).ceil - (dx * y).to_i }.sum
end

# Test
class PaperTest < MiniTest::Unit::TestCase
  def test_sample
    assert_equal 80, squares(8, 12)
  end

  def test_simple
    assert_equal 0, squares(1, 1)
    assert_equal 2, squares(2, 2)
    assert_equal 6, squares(3, 3)
  end

  def test_small
    assert_equal 2, squares(3, 2)
    assert_equal 2, squares(2, 3)
    assert_equal 6, squares(2, 6)
    assert_equal 4, squares(2, 5)
  end

  def test_edge
    assert_equal 8, squares(3, 5)
  end
end
