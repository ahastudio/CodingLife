# frozen_string_literal: true

require 'minitest/autorun'

def squares(width, height)
  h, w = [width, height].sort
  (w * h) - (0...h).map { |y| ((y + 1).to_f * w / h).ceil - y * w / h }.sum
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

    [*1..100].combination(2).each do |w, h|
      assert squares(w, h).even?, "w: #{w}, h: #{h}"
    end
  end
end
