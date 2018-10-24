# frozen_string_literal: true

require 'minitest/autorun'
require 'minitest/reporters'

Minitest::Reporters.use!

def spiral_matrix(size)
  m = Array.new(size) { Array.new(size) }
  (0...size**2).each do |i|
    x, y = spiral_pos(size, i)
    m[y][x] = i
  end
  m
end

def spiral_pos(size, index)
  spiral_pos_center(size) ||
    spiral_pos_top(size, index) ||
    spiral_pos_right(size, index) ||
    spiral_pos_bottom(size, index) ||
    spiral_pos_left(size, index) ||
    spiral_pos(size - 2, index - (size - 1) * 4).map { |i| i + 1 }
end

def spiral_pos_center(size)
  return [] if size < 1
  return [0, 0] if size == 1
end

def spiral_pos_top(size, index)
  index < size - 1 && [index, 0]
end

def spiral_pos_right(size, index)
  index -= size - 1
  index < size - 1 && [size - 1, index]
end

def spiral_pos_bottom(size, index)
  index -= (size - 1) * 2
  index < size - 1 && [size - 1 - index, size - 1]
end

def spiral_pos_left(size, index)
  index -= (size - 1) * 3
  index < size - 1 && [0, size - 1 - index]
end

# Spiral Matrix Test
class SpiralMatrixTest < MiniTest::Test
  def test_2x2
    assert_equal [
      [0, 1],
      [3, 2]
    ], spiral_matrix(2)
  end

  def test_3x3
    assert_equal [
      [0, 1, 2],
      [7, 8, 3],
      [6, 5, 4]
    ], spiral_matrix(3)
  end

  def test_4x4
    assert_equal [
      [0,  1,  2,  3],
      [11, 12, 13, 4],
      [10, 15, 14, 5],
      [9,  8,  7,  6]
    ], spiral_matrix(4)
  end

  def test_5x5
    assert_equal [
      [0,  1,  2,  3,  4],
      [15, 16, 17, 18, 5],
      [14, 23, 24, 19, 6],
      [13, 22, 21, 20, 7],
      [12, 11, 10, 9,  8]
    ], spiral_matrix(5)
  end

  def test_spiral_pos
    [
      [0, 0], [1, 0], [1, 1], [0, 1]
    ].each_with_index do |pos, index|
      assert_equal pos, spiral_pos(2, index), "size: 2, index: #{index}"
    end
    [
      [0, 0], [1, 0], [2, 0], [2, 1], [2, 2], [1, 2], [0, 2], [0, 1], [1, 1]
    ].each_with_index do |pos, index|
      assert_equal pos, spiral_pos(3, index), "size: 3, index: #{index}"
    end
  end
end
